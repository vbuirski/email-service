package au.com.vb.springboot.service;

import au.com.vb.springboot.exception.EmailException;
import au.com.vb.springboot.model.Email;
import au.com.vb.springboot.model.EmailResponse;

public interface EmailService {

  EmailResponse send(Email email) throws EmailException;

}
