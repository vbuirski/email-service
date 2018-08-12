package au.com.vb.springboot.model;

public class Email {
  private final long id;
  private final String to;
  private final String cc;
  private final String bcc;
  private final String subject;
  private final String content;

  public Email(long id, String to, String cc, String bcc, String subject, String content) {
    this.id = id;
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.subject = subject;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getTo() {
    return to;
  }

  public String getCc() {
    return cc;
  }

  public String getBcc() {
    return bcc;
  }

  public String getSubject() {
    return subject;
  }

  public String getContent() {
    return content;
  }
}
