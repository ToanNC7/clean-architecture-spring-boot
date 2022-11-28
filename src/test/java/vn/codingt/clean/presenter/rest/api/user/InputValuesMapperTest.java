package vn.codingt.clean.presenter.rest.api.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.codingt.clean.core.usecases.user.CreateAUserUseCase;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;
import vn.codingt.clean.presenter.rest.api.mapper.user.CreateAUserInputMapper;

import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.eq;

@RunWith(MockitoJUnitRunner.class)
public class InputValuesMapperTest {
    @InjectMocks
    private CreateAUserInputMapper inputMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void mapReturnsCreateCustomerInput() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("name", "email@email.com", "address", "password");

        CreateAUserUseCase.InputValues inputValues = SignUpRequest.from(signUpRequest);

        // and
        doReturn("encrypt")
                .when(passwordEncoder)
                .encode(eq("password"));
        // when
        CreateAUserUseCase.InputValues actual = inputMapper.map(signUpRequest);

        // then
        assertThat(actual).isEqualToIgnoringGivenFields(inputValues, "password");
        assertThat(actual.getPassword()).isEqualTo("encrypt");

    }

}
