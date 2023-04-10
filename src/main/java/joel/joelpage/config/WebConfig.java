package joel.joelpage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/empDetail/**")
                .allowedOrigins("http://joeladmin.store", "http://ec2-52-79-168-230.ap-northeast-2.compute.amazonaws.com:8000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
        registry.addMapping("/api/**")
                .allowedOrigins("http://joeladmin.store", "http://ec2-52-79-168-230.ap-northeast-2.compute.amazonaws.com:8000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

