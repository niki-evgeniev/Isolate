package shop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table
@Entity(name = "users")
public class User extends BaseEntity {

    private String email;
    private String firstName;
    private String lastName;

}
