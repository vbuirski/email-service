package au.com.vb.springboot.model;

public class Email {

  private String toList;
  private String ccList;
  private String bccList;
  private String subject;
  private String msg;

  public Email() {

  }

  public Email(String toList, String ccList, String bccList, String subject, String msg) {
    this.toList = toList;
    this.ccList= ccList;
    this.bccList = bccList;
    this.subject = subject;
    this.msg = msg;
  }

  public String getToList() {
    return toList;
  }

  public String getCcList() {
    return ccList;
  }

  public String getBccList() {
    return bccList;
  }

  public String getSubject() {
    return subject;
  }

  public String getMsg() {
    return msg;
  }
}
