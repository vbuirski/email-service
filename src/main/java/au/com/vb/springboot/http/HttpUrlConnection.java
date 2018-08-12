package au.com.vb.springboot.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpUrlConnection {

  private final String USER_AGENT = "Mozilla/5.0";

  public static void main(String[] args) throws Exception {

    HttpUrlConnection http = new HttpUrlConnection();

    System.out.println("Testing 1 - Send Http GET request");
    http.sendGet();

    System.out.println("\nTesting 2 - Send Http POST request");
    http.sendPost();

  }

  // HTTP GET request
  private void sendGet() throws Exception {

    String url = "http://www.google.com/search?q=mkyong";

    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    // optional default is GET
    con.setRequestMethod("GET");

    //add request header
    con.setRequestProperty("User-Agent", USER_AGENT);

    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'GET' request to URL : " + url);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    //print result
    System.out.println(response.toString());

  }

  // HTTP POST request
  private void sendPost() throws Exception {

    String url = "https://api.mailgun.net/v3/sandbox2d08c193bd834ba18b7163def4192b90.mailgun.org/messages";
    URL obj = new URL(url);
    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

    //add request header

    String basicAuth = "Basic YXBpOmU1ZjE1OWZmOWM0MGNjNmY2ZGQ5Y2RjMmU0Mzg3YzM4LTdlZmU4ZDczLWFhM2VlYWQz";
    con.setRequestProperty("Authorization", basicAuth);
    con.setRequestProperty("api", "e5f159ff9c40cc6f6dd9cdc2e4387c38-7efe8d73-aa3eead3");
    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", USER_AGENT);
    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

    String urlParameters = "from=postman@sandbox2d08c193bd834ba18b7163def4192b90.mailgun.org&" +
            "to=vonita.buirski@gmail.com&" +
            "subject=Surprise!!&" +
            "text=Guess who?!";

    // Send post request
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(urlParameters);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'POST' request to URL : " + url);
    System.out.println("Post parameters : " + urlParameters);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    //print result
    System.out.println(response.toString());

  }

}
