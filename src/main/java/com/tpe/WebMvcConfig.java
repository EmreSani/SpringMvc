package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe")
@EnableWebMvc//MVC yi etkinleştirmek için
public class WebMvcConfig implements WebMvcConfigurer {

    //view resolver
    @Bean
    public InternalResourceViewResolver resolver(){

        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");//view dosyası nerede
        resolver.setSuffix(".jsp");//dosyanın uzantısı nedir
        resolver.setViewClass(JstlView.class);
        //JSTL:JavaStandartTagLibrary:JSP içinde daha az Java kodu yazmamızı sağlar
        return resolver;
    }


    //statik sayfa içeren isteklerin servleta gönderilmesine gerek yok
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
          registry.addResourceHandler("/statics/**").//bu url ile gelen istekleri statik olarak sun
          addResourceLocations("/resources/").//statik kaynakların yeri
          setCachePeriod(0);//cache periyodu için süre verilebilir.
    }
}
