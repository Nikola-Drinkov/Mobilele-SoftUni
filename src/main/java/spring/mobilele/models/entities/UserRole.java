package spring.mobilele.models.entities;

import jakarta.persistence.*;
import spring.mobilele.enums.UserRolesEnum;

@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRolesEnum name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRolesEnum getName() {
        return name;
    }

    public void setName(UserRolesEnum name) {
        this.name = name;
    }
}
