package pe4nik.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "pe4nik.config")
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public TemplateResolver templateResolver(){
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setPrefix("/webapp");
//        templateResolver.setSuffix(".jsp");
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine()
//    {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        return templateEngine;
//    }
//
//    @Bean
//    public ViewResolver getViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setOrder(1);
//        return resolver;
//    }


}