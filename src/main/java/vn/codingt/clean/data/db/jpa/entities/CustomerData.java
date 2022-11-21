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
import vn.codingt.clean.core.domain.Customer;
import vn.codingt.clean.core.domain.Identity;

import static vn.codingt.clean.data.db.jpa.entities.IdConverter.converterId;

@AllArgsConstructor
@Entity(name = "customer")
@EqualsAndHashCode(of = { "name", "email", "address", "password" })
@Getter
@NoArgsConstructor
@Setter
@Table(name = "customer")
@ToString(of = { "name", "email", "address" })
public class CustomerData {

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

    public static CustomerData from(Customer customer) {
        return new CustomerData(
                converterId(customer.getId()),
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPassword()

        );
    }

    public Customer fromThis() {
        return new Customer(
                new Identity(id),
                name,
                password,
                address,
                email);
    }

}
