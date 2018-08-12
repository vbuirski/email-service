package au.com.vb.springboot.model;

public class EmailResponse {

  private Boolean successFlag;
  private String errorCode;

  public EmailResponse(Boolean successFlag, String errorCode) {
    this.successFlag = successFlag;
    this.errorCode = errorCode;
  }

  public Boolean getSuccessFlag() {
    return successFlag;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
