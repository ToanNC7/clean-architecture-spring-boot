package vn.codingt.clean.data.db.jpa.entities;

import org.junit.Test;

import vn.codingt.clean.TestEEntityGenerator;
import vn.codingt.clean.core.domain.Identity;

import static org.assertj.core.api.Assertions.assertThat;

public class IdConverterTest {

    @Test
    public void convertIdShouldReturnNumberWhenIsNotNullOrEmpty() {

        // given
        Long number = TestEEntityGenerator.randomId();

        // when
        Long actual = IdConverter.converterId(new Identity(number));

        // then

        assertThat(actual).isEqualTo(number);
    }

    @Test
    public void convertIdShouldReturnNullWhenInputIsNull() {
        // when
        Long actual = IdConverter.converterId(null);

        // then
        assertThat(actual).isNull();
    }

    @Test
    public void convertIdShouldReturnNUllWhenInputIsIdentityEmpty() {
        // given
        Identity id = Identity.nothing();

        // when
        Long actual = IdConverter.converterId(id);

        // then
        assertThat(actual).isNull();

    }

}
