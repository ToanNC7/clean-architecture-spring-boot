package vn.codingt.clean.core.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Customer {
    private Identity id;
    private String name;
    private String password;
    private String address;
    private String email;

    public static Customer newInstance(String name, String password, String address, String email) {
        return new Customer(
                Identity.nothing(),
                name,
                password,
                address,
                email);
    }
}
