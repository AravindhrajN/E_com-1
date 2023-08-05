package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Pdao.pdao;
import com.example.demo.imagedao.imagedao;
import com.example.demo.model.Image;
import com.example.demo.model.Product;

@Controller
@RequestMapping("/")
public class Admin_Control {

	private imagedao imagedao;
	private Image image;
	private pdao pdao;

	public Admin_Control(com.example.demo.imagedao.imagedao imagedao, com.example.demo.model.Image image, pdao pdao) {
		this.imagedao = imagedao;
		this.image = image;
		this.pdao = pdao;
	}

	// ADMIN PAGE RENDERING
	@GetMapping("/get")
	public String view() {
		return "Admin";
	}

	@GetMapping("/imageupload/{id}")
	public String imgup(@PathVariable("id") int id, Model model) {
		model.addAttribute("productid", id);
		return "adminpage";
	}

	// ADD THE PRODUCT
	@PostMapping("/adddetails")
	public ResponseEntity<?> productdetails(@ModelAttribute Product pro) {
		pdao.save(pro);
		String link = "<a href='/imageupload/" + pro.getId() + "'>PLEASE UPLOAD IMAGE</a>";
		String response = "Successfully Saved " + pro.getId() + " " + link;

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

//ADD THE NEW IMAGE
	@PostMapping("/images/{id}")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {
		try {
			image.setId(id);
			String originalFilename = file.getOriginalFilename();
			image.setName(originalFilename);

			String contentType = file.getContentType();
			image.setContentype(contentType);

			byte[] data = file.getBytes();
			image.setData(data);

			// Save the image entity to the database
			Image savedImage = imagedao.save(image);
			return ResponseEntity.status(HttpStatus.OK)
					.body("Successfully Saved " + savedImage.getId() + " <a href=\"/get\">Go back to main page</a>");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//UPADATING THE PRODUCT PRICE
	@PutMapping("/change")
	public ResponseEntity<?> update(@RequestParam("id") int id, @RequestParam("value") int price, Product product) {
		try {
			Product pro = pdao.getReferenceById(id);
			pro.setPprice(price);
			int pprice = pro.getPprice();
			return ResponseEntity.status(HttpStatus.OK).body(
					"Successfully Saved " + pro.getId() + ": " + pprice + " <a href=\"/get\">Go back to main page</a>");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

//DELETE THE PRODUCT
	@DeleteMapping("/deleteproduct")
	public ResponseEntity<?> delete(@RequestParam("id") int id, Product product) {
		try {
			pdao.deleteById(id);

			imagedao.deleteById(id);
			String link = "<a href=\"/get\">Go back to main page</a>";
			return ResponseEntity.status(HttpStatus.OK).body("Sucessfully deleted " + id + " " + link);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
