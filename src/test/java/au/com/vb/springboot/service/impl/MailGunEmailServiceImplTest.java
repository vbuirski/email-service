package au.com.vb.springboot.service.impl;

import au.com.vb.springboot.controller.EmailController;
import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmailController.class, secure = false)
public class MailGunEmailServiceImplTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MailGunEmailServiceImpl mailGunEmailService;

  String emailJson = "{"
         + "\"toList\": \"jevon.buirski@gmail.com\","
         + "\"ccList\": \"\","
         + "\"bccList\": \"\","
         + "\"subject\": \"Email Success\","
         + "\"msg\": \"Hi\n\nRegards\nJB\"";

  private String endPoint = "https://api.mailgun.net/v3/sandbox2d08c193bd834ba18b7163def4192b90.mailgun.org/messages";

  @Before
  public void setUp() {

  }

  @Test
  public void createEmail() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    Email email = objectMapper.readValue(emailJson, Email.class);
    EmailResponse response = mailGunEmailService.sendEmail(email);

    Assert.assertEquals(response.getSuccessFlag(), "Ok");

  }

  @Test
  public void shouldThrowException() {

  }
}

