package shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.Entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByVerificationToken(String token);

//    List<User> findAllByActivateFalseAndVerificationTokenIsNotNullAndTokenCreatedBefore(LocalDateTime localDateTime);

    List<User> findByActivateFalseAndVerificationTokenIsNotNullAndTokenCreatedBefore(LocalDateTime localDateTime);

}
