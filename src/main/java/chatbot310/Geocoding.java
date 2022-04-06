package chatbot310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class Geocoding {

    private static HttpURLConnection connection;
    public static final int FORMATTED_ADDRESS=0;
    public static final int PLACE_ID=1;
    public static final int GEOMETRY_LAT=2;
    public static final int GEOMETRY_ING=3;
    public static final int COMPOUND_CODE=4;
    public static final int GLOBAL_CODE=5;
    public static final String[] addressTitle={ "[FORMATTED_ADDRESS] ",
                                                "[PLACE_ID] ",
                                                "[GEOMETRY_LAT] ",
                                                "[GEOMETRY_ING] ",
                                                "[COMPOUND_CODE] ",
                                                "[GLOBAL_CODE]",
                                                "[ADDRESS COMPONENTS] "};

    public static ArrayList<String> getGeocodingAddress(String address){
        BufferedReader reader;
        String line;
        StringBuffer responseContent=new StringBuffer();
        ArrayList<String> components=new ArrayList<String>();

        String addressInfo="";
        for(int i=0; i<address.length(); i++){
            if(address.charAt(i)!=' '){
                addressInfo=addressInfo+address.charAt(i);
            }
        }

        try{
            URL url=new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+addressInfo+",+CA&key=AIzaSyCdNdUwN9Ef_rC43xshj-7qPEisUv37lns");
            connection=(HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status=connection.getResponseCode();
            if(status>299){
                reader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine())!=null){
                    responseContent.append(line);
                }
                reader.close();
            }else{
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine())!=null){
                    responseContent.append(line);
                }
                reader.close();
            }
            components.add(getFormattedAddress(responseContent.toString()));
            components.add(getPlaceID(responseContent.toString()));
            components.add(getGeometryLat(responseContent.toString()));
            components.add(getGeometryIng(responseContent.toString()));
            components.add(getCompoundCode(responseContent.toString()));
            components.add(getGlobalCode(responseContent.toString()));
            components.addAll(getAddressComponents(responseContent.toString()));
            return components;
        }catch (MalformedURLException | ProtocolException e){
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }
    private static ArrayList<String> getAddressComponents(String address){
        ArrayList<String> components=new ArrayList<String>();
        address=address.substring(address.indexOf("address_components"),address.indexOf("formatted_address"));
        while(address.indexOf("long_name")!=-1){
            address=address.substring(address.indexOf("long_name"));
            String readyToSave=address.substring(0, address.indexOf("}"));
            String addressInfo="";
            for(int i=0; i<readyToSave.length(); i++){
                if(readyToSave.charAt(i)!=' '){
                    addressInfo=addressInfo+readyToSave.charAt(i);
                }
            }

            components.add(addressInfo);
            address=address.substring(address.indexOf("}"));
        }

        return components;
    }
    private static String getFormattedAddress(String address){
        address=address.substring(address.indexOf("formatted_address")+22);
        return address.substring(0,address.indexOf("\","));
    }
    private static String getGeometryLat(String address){
        if(address.indexOf("lat")!=-1){
            address=address.substring(address.indexOf("lat")+7);
            return address.substring(0,address.indexOf(","));
        }else{
            return " ";
        }
    }
    private static String getGeometryIng(String address){
        if(address.indexOf("lng")!=-1){
            address=address.substring(address.indexOf("lng")+7);
            return address.substring(0,address.indexOf("}"));
        }else{
            return " ";
        }
    }
    private static String getPlaceID(String address){
        if(address.indexOf("place_id")!=-1){
            address=address.substring(address.indexOf("place_id")+13);
            return address.substring(0,address.indexOf("\","));
        }else{
            return " ";
        }
    }
    private static String getCompoundCode(String address){
        if(address.indexOf("compound_code")!=-1){
            address=address.substring(address.indexOf("compound_code")+18);
            return address.substring(0,address.indexOf("\","));
        }else{
            return " ";
        }
    }
    private static String getGlobalCode(String address){
        if(address.indexOf("global_code")!=-1){
            address=address.substring(address.indexOf("global_code")+16);
            return address.substring(0,address.indexOf("\" "));
        }else{
            return " ";
        }
    }

}
