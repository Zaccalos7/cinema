package com.orbis.cinema.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoggerMessageComponent {
    private final MessageSource messageSource;

    public String printMessage(String code){
        String message = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        return message;
    }

    public String printMessage(String code, Object[] params){
        String message = messageSource.getMessage(code , params, LocaleContextHolder.getLocale());
        return message;
    }
}
