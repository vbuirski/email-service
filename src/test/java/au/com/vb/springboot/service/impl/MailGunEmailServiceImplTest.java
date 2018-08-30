package au.com.vb.springboot.service.impl;

import au.com.vb.springboot.controller.EmailController;
import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import au.com.vb.springboot.service.MailGunEmailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmailController.class, secure = false)
public class MailGunEmailServiceImplTest {

  String emailJson = "{"
         + "\"toList\":\"vonita.buirski@gmail.com\","
         + "\"ccList\":\"\","
         + "\"bccList\":\"\","
         + "\"subject\":\"Email\","
         + "\"msg\":\"Regards\""
         + "}";

  private String endPoint = "https://api.mailgun.net/v3/sandbox2d08c193bd834ba18b7163def4192b90.mailgun.org/messages";

  MailGunEmailServiceImpl service;
  @Before
  public void setUp() {
    service = new MailGunEmailServiceImpl();
  }

  @Test
  public void createEmail() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    Email email = objectMapper.readValue(emailJson, Email.class);
    EmailResponse response = service.send(email);

    Assert.assertEquals(response.getSuccessFlag(), true);

  }

  @Test
  public void shouldThrowException() {

  }
}

