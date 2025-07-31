package shop.AppConfiguration;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.time.Duration;
import java.util.Locale;

@Configuration
public class I18nConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(I18nConfiguration.class);

    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver() {
            {
                setDefaultLocale(Locale.ENGLISH);
                setCookieMaxAge(Duration.ofDays(365));
            }

            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                Locale locale = super.resolveLocale(request);

                if (locale == null || locale.getLanguage().isEmpty()) {
                    String headerLang = request.getHeader("Accept-Language");

                    if (headerLang != null && headerLang.startsWith("bg")) {
                        logger.info("🌍 Автоматично избран език: BG (на база Accept-Language: {})", headerLang);
                        return Locale.forLanguageTag("bg");
                    } else {
                        logger.info("🌍 Автоматично избран език: EN (на база Accept-Language: {})", headerLang);
                        return Locale.ENGLISH;
                    }
                }

                logger.info("🌍 Езикът е зареден от cookie: {}", locale.getLanguage());
                return locale;
            }
        };
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
