
 function validateForm() {
  

   
    const nameInput = document.querySelector('input[name="ename"]');
    const mobileInput = document.querySelector('input[name="phone"]');
    const emailInput = document.querySelector('input[name="Email"]');
    const stateInput = document.querySelector('input[name="state"]');
    const cityInput = document.querySelector('input[name="City"]');
    const pincodeInput = document.querySelector('input[name="Pincode"]');
    const addressInput = document.querySelector('textarea[name="Address"]');


    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const phoneRegex = /^[0-9]+$/;
    const pincodeRegex = /^[0-9]{6}$/;


    if (nameInput.value.trim() === "") {
      alert("Please enter your name.");
      nameInput.focus();
      return false;
    }

    if (mobileInput.value.trim() === "" || !phoneRegex.test(mobileInput.value)) {
      alert("Please enter a valid mobile number containing only numbers.");
      mobileInput.focus();
      return false;
    }

    if (emailInput.value.trim() === "" || !emailRegex.test(emailInput.value)) {
      alert("Please enter a valid email address.");
      emailInput.focus();
      return false;
    }

    if (stateInput.value.trim() === "") {
      alert("Please enter your state.");
      stateInput.focus();
      return false;
    }

    if (cityInput.value.trim() === "") {
      alert("Please enter your city.");
      cityInput.focus();
      return false;
    }

    if (pincodeInput.value.trim() === "" || !pincodeRegex.test(pincodeInput.value)) {
      alert("Please enter a valid pincode containing only 6 digits.");
      pincodeInput.focus();
      return false;
    }

    if (addressInput.value.trim() === "") {
      alert("Please enter your address.");
      addressInput.focus();
      return false;
    }

     return true;
  }

