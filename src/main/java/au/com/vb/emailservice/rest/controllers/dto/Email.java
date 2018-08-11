package au.com.vb.emailservice.rest.controllers.dto;

public class Email {
  private final long id;
  private final String content;

  public Email(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
