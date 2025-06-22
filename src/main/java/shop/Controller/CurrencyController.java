package shop.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class CurrencyController {

    @GetMapping("/currency")
    public void handleCurrency(@RequestParam("currency") String currency,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {

        request.getSession().setAttribute("currency", currency);
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer != null ? referer : "/");
    }

}
