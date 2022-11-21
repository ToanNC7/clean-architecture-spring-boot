package vn.codingt.clean.core.entities;

import com.github.javafaker.Faker;

import vn.codingt.clean.core.domain.Customer;
import vn.codingt.clean.core.domain.Identity;

public final class TestCoreEntityGenerator {

    private static final Faker faker = new Faker();

    public static Customer randomCustomer() {
        return new Customer(
                randomId(),
                faker.name().name(),
                faker.lorem().fixedString(30),
                faker.address().fullAddress(),
                faker.internet().emailAddress());
    }

    private static Identity randomId() {
        return new Identity(faker.number().randomNumber());
    }

}
