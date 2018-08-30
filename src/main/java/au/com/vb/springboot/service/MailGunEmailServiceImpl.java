package au.com.vb.springboot.service;

import au.com.vb.springboot.exception.EmailException;
import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import au.com.vb.springboot.service.EmailService;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Qualifier("primary")
public class MailGunEmailServiceImpl implements EmailService {

  private final String USER_AGENT = "Mozilla/5.0";

  @Override
  public EmailResponse send(Email email) throws EmailException {

    try {
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
              "to=" + email.getToList() + "&" +
              "subject=" + email.getSubject() + "&" +
              "text=" + email.getMsg();

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

    } catch (Exception e) {
        throw new EmailException(e.getMessage());
    }

    return new EmailResponse(true, "");
  }

  public boolean sendMessage(String message) {
    return false;
  }
}



