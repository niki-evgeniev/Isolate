package shop.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ResponseCommitCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (response.isCommitted()) {
            System.err.println(
                    "[⚠] WARNING: Response is already committed BEFORE reaching controller! URL: "
                            + request.getRequestURI());
        }
        return true;
    }
}
