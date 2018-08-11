package au.com.vb.emailservice.service;

import au.com.vb.emailservice.rest.controllers.dto.Email;

public interface EmailService {

  String createEmail(Email email);

}
