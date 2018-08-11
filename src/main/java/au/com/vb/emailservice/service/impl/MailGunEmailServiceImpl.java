package au.com.vb.emailservice.service.impl;

import au.com.vb.emailservice.rest.controllers.dto.Email;
import au.com.vb.emailservice.service.EmailService;

public class MailGunEmailServiceImpl implements EmailService {

  @Override
  public String createEmail(Email email) {


    return "Created MailGun Email";
  }


}
