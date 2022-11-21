package vn.codingt.clean;

import com.github.javafaker.Faker;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.codingt.clean.presenter.usecases.secutiry.UserPrincipal;

import java.util.Collections;

public class TestEEntityGenerator {

    private static final Faker faker = new Faker();

    public static Long randomId() {
        return faker.number().randomNumber();
    }

    public static UserPrincipal randomUserPrincipal() {
        return new UserPrincipal(
                randomId(),
                faker.name().username(),
                randomEmail(),
                randomPassword(),
                faker.address().fullAddress(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public static String randomPassword() {
        return faker.code().isbn10();
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

}
