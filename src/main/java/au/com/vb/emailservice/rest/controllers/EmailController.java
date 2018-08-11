package au.com.vb.emailservice.rest.controllers;

import au.com.vb.emailservice.rest.controllers.dto.Email;
import au.com.vb.emailservice.rest.controllers.dto.Greeting;
import au.com.vb.emailservice.service.EmailService;
import au.com.vb.emailservice.service.impl.MailGunEmailServiceImpl;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(),
            String.format(template, name));
  }

  @RequestMapping("/send-email")
  public String createEmail(@RequestParam(value="name", defaultValue="World") String name) {
    EmailService emailService = new MailGunEmailServiceImpl();

    return emailService.createEmail(new Email(1, "vb hi"));
  }
}
