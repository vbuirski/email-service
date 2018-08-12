package au.com.vb.springboot.service.impl;

import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import au.com.vb.springboot.service.EmailService;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Service;

@Service
public class SendGridEmailServiceImpl implements EmailService {

  private final String USER_AGENT = "Mozilla/5.0";

  @Override
  public EmailResponse sendEmail(Email email) {

    try {
      String url = "https://api.sendgrid.com/v3/mail/send";
      URL obj = new URL(url);
      HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

      //add request header

      String bearerToken = "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";

      con.setRequestProperty("Authorization", "Bearer " + bearerToken);
      con.setRequestProperty("api", "e5f159ff9c40cc6f6dd9cdc2e4387c38-7efe8d73-aa3eead3");
      con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("User-Agent", USER_AGENT);
      con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

      String json = buildJson();

      // Send post request
      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.writeBytes(json);
      wr.flush();
      wr.close();

      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'POST' request to URL : " + url);
      System.out.println("Post parameters : " + json);
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
    } catch (Exception ex) {
      return new EmailResponse(false,ex.getMessage());
    }

    return new EmailResponse(true, "");
  }

  private String buildJson() {

  String content = " { "
    + "\"personalizations\": ["
    + "  { "
    + "   \"to\": [ "
    + "   { "
    + "     \"email\": \"vonita.buirski@gmail.com\", "
    + " \"name\": \"John Doe\" "
    + "   } "
    + " ], "
    + "   \"subject\": \"Hello, World!\" "
    + " } "
  + "],"
     + " \"from\": { "
     + " \"email\": \"sam.smith@example.com\", "
     + "        \"name\": \"Sam Smith\" "
    + " } , "
    + "\"reply_to\": { "
     + "\"email\": \"vonita.buirski@gmail.com\","
     + "\"name\": \"Sam Smith\""
   + " },"
   + "  \"content\": [ "
    + " {"
    + "   \"type\": \"text/plain\", "
      + "       \"value\": \"hello\" "
     + " } "
 + " ] "
    + "}";
    return content;
  }
}
