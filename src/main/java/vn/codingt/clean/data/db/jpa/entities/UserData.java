package vn.codingt.clean.data.db.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.domain.Identity;

import static vn.codingt.clean.data.db.jpa.entities.IdConverter.converterId;

@AllArgsConstructor
@Entity(name = "user")
@EqualsAndHashCode(of = { "name", "email", "address", "password" })
@Getter
@NoArgsConstructor
@Setter
@Table(name = "t_user")
@ToString(of = { "name", "email", "address" })
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    public static UserData from(User user) {
        return new UserData(
                converterId(user.getId()),
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getPassword()

        );
    }

    public User fromThis() {
        return new User(
                new Identity(id),
                name,
                password,
                address,
                email);
    }

}
