package au.com.vb.emailservice.service.impl;

import au.com.vb.emailservice.rest.controllers.dto.Email;
import au.com.vb.emailservice.service.EmailService;

public class SendGridEmailServiceImpl implements EmailService {

  @Override
  public String createEmail(Email email) {
    return "Created SendGridEmailService";
  }
}
