import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class ExChangeAPI {
    //class attributer
    private String result;
    private int time_last_update_unix;
    private String time_last_update_utu;
    private int time_next_update_unix;
    private String time_naxt_update_utu;
    private String base_code;
    private JSONObject eachRate;

    private static String url_API ="https://v6.exchangerate-api.com/v6/ba10e2218a82bd3bf6dc9c82/latest/";
    private static JSONObject jsonObject;

    //connect to sever
    public boolean getConnection(String base_code){
        String json ="";
        URL url = null;
        HttpURLConnection requset = null;

        try {
            url = new URL (url_API + base_code);
            requset =(HttpURLConnection) url.openConnection();
            //connect to sever
            requset.connect();
            //read data
            BufferedReader reader = new  BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();

            if (line.length() >0){
                json += line;
            }

            //json to jsonObject
            jsonObject = new JSONObject(json);
            if (jsonObject == null){
                return  false;
            }
            this.result = jsonObject.getString("result");
            this.base_code = jsonObject.getString("base_code");
            this.eachRate = jsonObject.getJSONObject("conversion_rates");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    public String getResult() {
        return result;
    }

    public String getBase_code() {
        return base_code;
    }

    public JSONObject getEachRate() {
        return eachRate;
    }

    public double getEachRate(String newCurrency) {
        double eachRate = 0.0;
        try {
            eachRate = this.eachRate.getDouble(newCurrency);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eachRate;
    }
}
