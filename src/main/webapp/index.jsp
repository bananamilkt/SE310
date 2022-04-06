<%@ page import="chatbot310.Chatbot" %>
<%@ page import="chatbot310.Geocoding" %>
<%@ page import="chatbot310.Dict" %>
<%@ page import="chatbot310.SentimentAnalysis" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<title>INDEX</title>
</head>
<body onload="ToBottom()">
	<div class="container">
		<form>
			<input class="reset" type="submit" name="reset" value="RESET">
		</form>
		<div class="header">
			<h3>Oculus Support</h3>
		</div>
		<div class="body" id="chatbox">
			<div class="chatbox">
				<p class="message">TechSupport: Hello, welcome to VR/AR support. Please type your question below:</p>	
					<%
						session = request.getSession(true);
						ArrayList<String> conversations=new ArrayList<String>();
						Chatbot b=new Chatbot();
						if(session.getAttribute("cart") != null){
							session.removeAttribute("cart");
						}
						
						if(request.getParameter("reset") != null){
							if(session.getAttribute("conversations") != null){session.removeAttribute("conversations");}
							if(session.getAttribute("chatbot") != null){session.removeAttribute("chatbot");}
						}else{
							if(session.getAttribute("conversations") != null){
								conversations=(ArrayList<String>)session.getAttribute("conversations");
							}else{
								session.setAttribute("conversations", conversations);
							}
							if(session.getAttribute("chatbot") != null){
								b=(Chatbot)session.getAttribute("chatbot");
							}else{
								session.setAttribute("chatbot", b);
							}
						
							if(request.getParameter("userInput") != null){
								conversations.add(request.getParameter("userInput"));
								conversations.add(b.getResponse(request.getParameter("userInput")));
							}
							for(int i=0; i<conversations.size(); i++){
								if(conversations.get(i)!=null){
									if(conversations.get(i).indexOf("TechSupport:")==0){
										String sentence=conversations.get(i);
										while(sentence.indexOf("/n")!=-1){
											if(sentence.length()!=0 && sentence!=" "){out.print("<p class=\"message\">"+sentence.substring(0,sentence.indexOf("/n"))+"</p>");}	
											sentence=sentence.substring(sentence.indexOf("/n")+2);
										}
										if(sentence.length()!=0 && sentence!=" "){out.print("<p class=\"message\">"+sentence+"</p>");}		
										if(conversations.get(i).indexOf("OrderSummary:")!=-1){
											out.print("<form class=\"input\" method=\"POST\" action=\"payment.jsp\">"+
											 		"<input class=\"submitOrder\" type=\"submit\" name=\"Submit Order\" value=\"Submit Order\">" +
											  "</form>");
											session.setAttribute("cart", conversations.get(i));
										}
									}else{
										out.print("<p class=\"message user_message\">"+conversations.get(i)+"</p>");
									}
								}
							}
						}
						
						//Testing chatbot
						//Chatbot b=new Chatbot();
						//out.print("<p class=\"message\">"+b.getResponse()+"</p>");
						//Testing chatbot

						//Testing Dict correctSpelling(String sentence)
						//try{
						//	Dict d=new Dict();
						//	out.print("<p class=\"message user_message\">"+d.extendSentence("This is a misspellin sentencee.")+"</p>");
						//}catch(Exception e){
						//	e.printStackTrace();
						//}
						//Testing Dict
						
						//Testing StandfordNLP
						//SentimentAnalysis s=new SentimentAnalysis();
						//out.print("<p class=\"message user_message\">"+s.getSentimentAnalysis("This is a netural review.")+"</p>");
						//Testing StandfordNLP
						
						//Testing Geocoding
						//ArrayList<String> address=Geocoding.getGeocodingAddress("UBCO");
						//for(int i=0; i<conversations.size(); i++){
						//	out.print("<p class=\"message\">"+address.get(i)+"</p>");	
						//}
						//Testing Geocoding
						
					%>							
			</div>
		</div>
		<div class="footer">
			<form class="input">
				<input class="inputText" type="text" name="userInput" placeholder="Chat with the AI support agent" required>
				<button>SEND</button>
			</form>
		</div>
	</div>
</body>

<script>
	function ToBottom(){
	var chatbox=document.getElementById("chatbox");
	chatbox.scrollTop=chatbox.scrollHeight;
	}
	
</script>

</html>

