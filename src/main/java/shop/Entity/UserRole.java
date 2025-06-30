package shop.Entity;

import jakarta.persistence.*;
import shop.Entity.Enums.RoleType;

@Table
@Entity(name = "roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public UserRole() {
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
