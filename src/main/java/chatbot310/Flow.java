package chatbot310;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Flow object is where a response requires a set of fixed actions to complete a task
 * Example: If user is asking for account information, flow would be fixed
 * actions of getting username and email.
 */
public class Flow {
    public String title;
    public ArrayList<String> responseID;
    public ArrayList<String> responses;
    public ArrayList<String> pattern_format;

    public Flow(ArrayList<String> responseID, ArrayList<String> responses, ArrayList<String> patterns, String title) {
        this.responses = responses;
        this.pattern_format = patterns;
        this.title = title;
        this.responseID = responseID;
    }

    /**
     * Takes user input and matches it to a pattern in 'pattern_format'
     */
    public boolean matches(int tick, String userInput) {
        if (pattern_format.get(tick).isEmpty()) {
            return true;
        }
        return userInput.matches(pattern_format.get(tick));
    }

    public String saveInputs(ArrayList<String> inputs) {
    	String returnMessage ="";
        int sentimentAnalysis_score=0;
        SentimentAnalysis sentimentAnalysis=new SentimentAnalysis();
        sentimentAnalysis_score=sentimentAnalysis.getSentimentAnalysis(inputs.get(responseID.indexOf("Review")));

        try {
            File review = null;
            if(sentimentAnalysis_score > 0){
                review = new File("GoodReviews.txt");
            }
            if(sentimentAnalysis_score == 0){
                review = new File("NeutralReviews.txt");
            }
            if(sentimentAnalysis_score < 0){
                review = new File("BadReviews.txt");
            }
            review.createNewFile();
            FileWriter fileWriter = new FileWriter(review,true);
            fileWriter.write(
                "------" + 
                inputs.get(responseID.indexOf("Name")) + "|"+ 
                inputs.get(responseID.indexOf("Email")) +
                "------\n" + 
                inputs.get(responseID.indexOf("Review")) + "\n"
            );
            fileWriter.close();
            
            returnMessage=returnMessage+"Reviewer:"+inputs.get(responseID.indexOf("Name")) + "/nContactEmail:"+inputs.get(responseID.indexOf("Email"))+"/nFeedback:"+inputs.get(responseID.indexOf("Review")) + "/n";
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnMessage;
    }
    public String processingPurchase(ArrayList<String> inputs){
    	String returnMessage="";
    	ArrayList<String> addressInfo=new ArrayList<String>();
    	returnMessage=returnMessage+"OrderSummary:";
		try {
            File orderHistory = null;
            orderHistory = new File("OrderHistory.txt");
            
            orderHistory.createNewFile();
            FileWriter fileWriter = new FileWriter(orderHistory,true);
            fileWriter.write("[Order Information]-------------------------------------------------------------------------------------------\n");
            fileWriter.write(
                                "\n[Receiver Information]---------------\n" +
                "[Receiver Name] " + inputs.get(responseID.indexOf("Name")) + "\n" + 
                "[Receiver Email] " + inputs.get(responseID.indexOf("Email")) + "\n" + 
                "[Receiver Phone Number] " + inputs.get(responseID.indexOf("Phone number")) + "\n" +
                                "\n[Order Detail]-----------------------\n" +
                "[ProductID] " + inputs.get(responseID.indexOf("Product")) + "\n" +
                "[Quantity] " + inputs.get(responseID.indexOf("Quantity")) + "\n"
            );

            fileWriter.write(   "\n[Shipping Address]-------------------\n");
            addressInfo=Geocoding.getGeocodingAddress(inputs.get(responseID.indexOf("Address")));
            for(int i=0; i<addressInfo.size(); i++){
                if(i<=5){fileWriter.write(Geocoding.addressTitle[i]);}
                if(i==6){fileWriter.write(Geocoding.addressTitle[6] + "\n");}
                fileWriter.write(addressInfo.get(i) + "\n");
            }
            fileWriter.write("--------------------------------------------------------------------------------------------------------------\n");

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        returnMessage=returnMessage+"/nProductID:"+inputs.get(responseID.indexOf("Product"));
        returnMessage=returnMessage+"/nQuantity:"+inputs.get(responseID.indexOf("Quantity"));
        returnMessage=returnMessage+"/nReceiverName:"+inputs.get(responseID.indexOf("Name"));
        returnMessage=returnMessage+"/nReceiverEmail:"+inputs.get(responseID.indexOf("Email"));
        returnMessage=returnMessage+"/nReceiverPhoneNumber:"+inputs.get(responseID.indexOf("Phone number"));
        returnMessage=returnMessage+"/nReceiverAddress:"+addressInfo.get(Geocoding.FORMATTED_ADDRESS)+"/n";
        
        return returnMessage;
    }
}
