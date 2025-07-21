package shop.AppConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import shop.Interceptor.ResponseCommitCheckInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final LocaleChangeInterceptor localeChangeInterceptor;
    private final ResponseCommitCheckInterceptor responseCommitCheckInterceptor;

    public WebConfiguration(LocaleChangeInterceptor localeChangeInterceptor,
                            ResponseCommitCheckInterceptor responseCommitCheckInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
        this.responseCommitCheckInterceptor = responseCommitCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(responseCommitCheckInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/imagesApp/**")
               .addResourceLocations("file:./imagesApp/");
    }
}
