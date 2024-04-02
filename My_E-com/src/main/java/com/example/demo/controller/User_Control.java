package com.example.demo.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Edao.Edao;
import com.example.demo.Odao.Odao;
import com.example.demo.Pdao.pdao;
import com.example.demo.imagedao.imagedao;
import com.example.demo.model.Cart;
import com.example.demo.model.Image;
import com.example.demo.model.Orderlist;
import com.example.demo.model.Product;
import com.example.demo.model.Userdetails;

import jakarta.servlet.http.HttpSession;

@Controller
public class User_Control {

	public User_Control() {
		// Default constructor
	}

	private pdao pdao;
	private imagedao imagedao;
	private Edao edao;
	private Odao odao;
	private int addQ = 1;

	@Autowired
	public User_Control(com.example.demo.Pdao.pdao pdao, imagedao imagedao, Edao edao, Odao odao) {
		this.pdao = pdao;
		this.imagedao = imagedao;
		this.edao = edao;
		this.odao = odao;

	}

	@GetMapping()
	public String home(Model model) {
		// ADD THE PRODUCT
		List<Product> pro = pdao.findAll();
		model.addAttribute("products", pro);
		return "User";
	}

	@GetMapping("/getimage/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
		try {
			// Retrieve the image from the database based on the ID
			Image image = imagedao.findById(id).orElse(null);

			if (image == null) {
				return ResponseEntity.notFound().build();
			}

			// Get the image data as a byte array
			byte[] imageData = image.getData();

			// Set the appropriate headers in the response
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//////// BUY////////
	@GetMapping("/buy")
	public String buy(@RequestParam("id") int id, @RequestParam("size") String size, Model model) {
		Product pro = pdao.getReferenceById(id);
		model.addAttribute("products", pro);
		model.addAttribute("size", size);
		return "form";
	}

///////CART//////
	LinkedList<Cart> list = new LinkedList<Cart>();

	@GetMapping("/cart")
	public String cart(@RequestParam("id") int id, @RequestParam("size") String size, Cart c) {
		Cart cart = new Cart();
		cart.setId(id);
		cart.setSize(size);
		Product pro = pdao.getReferenceById(id);
		cart.setPname(pro.getPname());
		cart.setPprice(pro.getPprice());
		cart.setQuantity(1);
		String check = c.check(id, size, list);
		if (check.equalsIgnoreCase("false")) {
			for (Cart a : list) {
				System.out.print(a.getPname());
			}
			list = c.addproducts(cart, list);
			System.out.print("addp");
		}
		System.out.println("added");

		return "redirect:user";

	}

	@GetMapping("/mycart")
	public String mycart(Model model) {

		model.addAttribute("Cart", list);
		return "Mycart";
	}

	@GetMapping("/cartmin")
	public String cartmin(@RequestParam("qa") int q, @RequestParam("id") int id, Cart c) {
		q -= 1;
		c.setquantitybyId(id, q, list);
		return "redirect:mycart";

	}

	@GetMapping("/cartmax")
	public String cartmax(@RequestParam("qa") int q, @RequestParam("id") int id, Cart c) {
		q += 1;
		c.setquantitybyId(id, q, list);
		return "redirect:mycart";

	}

	@GetMapping("/remove")
	public String remove(@RequestParam("id") int id, Cart c) {
		c.removeElementbyId(id, list);
		return "redirect:mycart";
	}

///////form////////
	LinkedList<Userdetails> list1 = new LinkedList<Userdetails>();

	@PostMapping("/userdata")
	public String form(Userdetails euser, Model model) {

		if (euser.getProductid() == 0) {
			for (Userdetails user : list1) {
				user.setQuantity(addQ);
				System.out.println("change2 " + user.getQuantity());
			}
		} else {

			euser.setQuantity(1);
			list1.add(euser);
			System.out.println("change1 " + addQ);
		}

		Product pro = pdao.getReferenceById(list1.get(0).getProductid());

		for (Userdetails user : list1) {
			System.out.println(user.getSize() + " " + user.getQuantity());

			model.addAttribute("user", user);
			model.addAttribute("product", pro);
		}
		return "ordersummary";
	}

	////// order summary///

	@PostMapping("/order")
	public String order(Orderlist order, Model model, HttpSession session) {

		String refid = (String) session.getAttribute("refid");
		if (refid == null) {
			refid = referenceid();
			session.setAttribute("refid", refid);
		}

		for (Userdetails user : list1) {
			user.setId(refid);
			edao.save(user);
		}

		String orderid = (String) session.getAttribute("orderid");
		if (orderid == null) {
			orderid = orderid();
			session.setAttribute("orderid", orderid);
		}
		order.setOrderid(orderid);
		order.setRefid(refid);
		odao.save(order);

		model.addAttribute("orderid", order.getOrderid());
		model.addAttribute("refid", refid);
		return "payment";
	}

	@GetMapping("/ordermin")
	public String ordermin(@RequestParam("qa") int q) {
		System.out.println("ordermax method called with q = " + q);

		--q;
		System.out.println("orderbefore " + addQ);
		addQ = q;
		System.out.println("order " + addQ);
		return "redirect:userdata";

	}

	@GetMapping("/ordermax")
	public String ordermax(@RequestParam("qa") int q) {

		System.out.println("ordermax method called with q = " + q);

		++q;
		System.out.println("orderbefore " + addQ);
		addQ = q;
		System.out.println("order " + addQ);

		return "redirect:userdata";

	}

	/*************** ABOUT *******************/
	@GetMapping("/about")
	public String about() {
		return "About";
	}

	@GetMapping("/service")
	public String service() {
		return "service";
	}

	@GetMapping("/contactus")
	public String contactus() {
		return "contact";
	}

	/********************** generate-referenceid ****************/
	public String referenceid() {
		String refid = "";
		String num = "0123456789";
		String schar = "!@#$%^&*";
		String ucase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lcase = "abcdefghijklmnopqrstuvwxyz";
		Random ran = new Random();
		for (int i = 0; i < 7; i++) {

			refid += String.valueOf(num.charAt(ran.nextInt(num.length())));
		}
		refid += String.valueOf(schar.charAt(ran.nextInt(schar.length())));
		refid += String.valueOf(ucase.charAt(ran.nextInt(ucase.length())));
		for (int i = 0; i < 3; i++) {
			refid += String.valueOf(lcase.charAt(ran.nextInt(lcase.length())));

		}
		return refid;
	}

	/**************** generate-orderid *********************/
	public String orderid() {
		String orderid = "";
		String num = "0123456789";
		Random ran = new Random();
		for (int i = 0; i < 16; i++) {

			orderid += String.valueOf(num.charAt(ran.nextInt(num.length())));
		}

		return orderid;
	}
}
