<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
<meta charset="ISO-8859-1">
<title>PayPal</title>
<script src="https://www.paypal.com/sdk/js?client-id=AbshCI1MRIvB8all4f4xn7kgNGK1kHye3KZ6OTrqbMsimMYYLXORTTXh_wjD9GubdcUpWb9Ns712FCQm"></script>
</head>
<body>
	<div class="paypalcontainer">
<%
	session = request.getSession(true);
	String cart="EMPTY";
	if(session.getAttribute("cart") != null){
		cart=(String)session.getAttribute("cart");
		session.removeAttribute("cart");
	}
	if(cart!="EMPTY"){
		String ReceiverName=cart.substring(cart.indexOf("ReceiverName:")+13,cart.indexOf("ReceiverEmail:")-2);
		String Quantity=cart.substring(cart.indexOf("Quantity:")+9,cart.indexOf("ReceiverName:")-2);
		int q=Integer.parseInt(Quantity);
		String Email=cart.substring(cart.indexOf("ReceiverEmail:")+14,cart.indexOf("ReceiverPhoneNumber:")-2);
		String Phone=cart.substring(cart.indexOf("ReceiverPhoneNumber:")+20,cart.indexOf("ReceiverAddress:")-2);
		String Address=cart.substring(cart.indexOf("ReceiverAddress:")+16,cart.indexOf("Thanks for using our support bot.")-2);
		String ProductID=cart.substring(cart.indexOf("ProductID:")+10,cart.indexOf("Quantity:")-2);
		String ProductName="";
		double price=0;
		if(ProductID.equals("1")){ProductName="NX1-Pro 128GB"; price=599*q;}
		if(ProductID.equals("2")){ProductName="NX1-Pro 512GB"; price=669*q;}
		if(ProductID.equals("3")){ProductName="NX2-Pro 128GB"; price=599*q;}
		if(ProductID.equals("4")){ProductName="NX2-Pro 512GB"; price=669*q;}
	

		out.print("<h2>Order Summary</h2>");
			
		out.print("<h5>Receiver Name:</h5>");
		out.print("<h4>"+ReceiverName+"</h4>");
		out.print("<h5>Receiver Email:</h5>");
		out.print("<h4>"+Email+"</h4>");
		out.print("<h5>Phone Number:</h5>");
		out.print("<h4>"+Phone+"</h4>");
		out.print("<h5>Shipping Address:</h5>");
		out.print("<h4>"+Address+"</h4>");
		out.print("<h5>Product Name:</h5>");
		out.print("<h4>"+ProductName+"</h4>");
		out.print("<h5>Quantity:</h5>");
		out.print("<h4>"+q+"</h4>");
		out.print("<h5>Total Price:</h5>");
		out.print("<input type=\"number\" class=\"priceDispaly\" id=\"amount\" value=\""+price+"\" readonly>");
		out.print("<div id=\"paypal\"></div>");

	}
%>
	</div>
</body>
<script>
const amountElement=document.getElementById("amount")
console.log(amountElement.value)

paypal.Buttons({
  createOrder: function(data, actions) {
    // This function sets up the details of the transaction, including the amount and line item details.
    return actions.order.create({
      purchase_units: [{
        amount: {
          value: amountElement.value,
        }
      }]
    });
  },
  onApprove: function(data, actions) {
    // This function captures the funds from the transaction.
    return actions.order.capture().then(function(details) {
      // This function shows a transaction success message to your buyer.
      alert('Transaction completed by ' + details.payer.name.given_name);
    });
  }
}).render('#paypal')
</script>


</html>