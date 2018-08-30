package au.com.vb.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"au.com.vb.springboot"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}