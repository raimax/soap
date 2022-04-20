package lt.viko.eif.rcepauskas.soap;

import lt.viko.eif.rcepauskas.blog.Blog;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "posts")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema postsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PostsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://localhost/web-service");
        wsdl11Definition.setSchema(postsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema postsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("posts.xsd"));
    }

    @Bean
    public UnitOfWork unitOfWork() {
        return new UnitOfWork();
    }

    @Bean
    public Blog blog() {
        return new Blog();
    }
}
