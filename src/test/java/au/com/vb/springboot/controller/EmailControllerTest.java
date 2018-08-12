package au.com.vb.springboot.controller;


import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;
import au.com.vb.springboot.service.impl.MailGunEmailServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
public class EmailControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MailGunEmailServiceImpl mailGunEmailService;

  Email mockEmail = new Email(1, "vonita.buirski@gmail.com", "","","hello", "my content");

  String emailJson = "{"
          + "\"toList\": \"jevon.buirski@gmail.com\","
          + "\"ccList\": \"\","
          + "\"bccList\": \"\","
          + "\"subject\": \"Email Success\","
          + "\"msg\": \"Hi\n\nRegards\nJB\"";

  private String endPoint = "/send-email";


  @Test
  public void sendEmail() throws Exception {

    // emailService.sendEmail to respond back with String
    Mockito.when(
            mailGunEmailService.sendEmail(Mockito.any(Email.class))).thenReturn(new EmailResponse(true, ""));

    // Send email as body to endpoint
    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post(endPoint)
            .accept(MediaType.APPLICATION_JSON).content("Success")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    MockHttpServletResponse response = result.getResponse();


    Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

  }

  @Test
  public void shouldThrowException() {

  }

}

