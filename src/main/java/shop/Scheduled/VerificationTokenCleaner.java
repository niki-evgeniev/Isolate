package shop.Scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shop.Entity.User;
import shop.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class VerificationTokenCleaner {

    private final UserRepository userRepository;

    public VerificationTokenCleaner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 0 */4 * * *")
    public void deleteExpiredUnverifiedUsers() {
        LocalDateTime expirationThreshold = LocalDateTime.now().minusHours(48);

        List<User> expiredUsers = userRepository
                .findByActivateFalseAndVerificationTokenIsNotNullAndTokenCreatedBefore(expirationThreshold);

        if (!expiredUsers.isEmpty()) {
            userRepository.deleteAll(expiredUsers);
            System.out.println("Изтрити непотвърдени акаунти: " + expiredUsers.size());
        }
    }
}
