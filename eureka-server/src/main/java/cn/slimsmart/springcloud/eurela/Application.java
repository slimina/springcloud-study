package cn.slimsmart.springcloud.eurela;

import com.netflix.appinfo.DataCenterInfo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Application {

  public static void main(String[] args) {
    // SpringApplication.run(Application.class,args);
    new SpringApplicationBuilder(Application.class).web(true).run(args);
  }
}
