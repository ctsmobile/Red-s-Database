package com.cts.redplastring.redplastringapplication.response;

import com.cts.redplastring.redplastringapplication.Config.WebMvcConfig;
import lombok.Data;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.Locale;

@Data
@PropertySources({@PropertySource("classpath:i18n/messages_es.properties"),@PropertySource("classpath:i18n/messages_en.properties")})
public class AbstractResponse {
    private String message;
    private String frontendMessage;

    /*public void setFrontendmessage(String frontendmessage, Locale locale) {
        if(locale==null) {
            locale = new Locale("en");
        }
        WebMvcConfig webMvcConfig = new WebMvcConfig();
        setFrontendmessage(webMvcConfig.getMessageResource().getMessage(frontendmessage,null,locale));
    }

    public void setFrontendmessage(String message) {
        this.frontendMessage = message;
    }*/

}
