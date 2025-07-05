package shop.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.DTO.EmailRequestDTO;
import shop.Service.Impl.MailjetService;

@RestController
@RequestMapping("/api")
public class EmailRestController {

    private final MailjetService mailjetService;

    public EmailRestController(MailjetService mailjetService) {
        this.mailjetService = mailjetService;
    }

    @PostMapping("/send")
    public String sendTestEmail(@RequestBody EmailRequestDTO dto) {
        boolean result = mailjetService.sendEmail(dto);
        System.out.println("EMAIL");
        return result ? "Имейлът е изпратен успешно!"
                      : "Неуспешно изпращане на имейл.";
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("🔥 Контролерът работи!");
        return "OK";
    }

    @PostMapping("/post")
    public ResponseEntity<?>testEmailTwo(@RequestBody EmailRequestDTO dto){
        System.out.println("test 2");
        return ResponseEntity.ok("OK");
    }
}
