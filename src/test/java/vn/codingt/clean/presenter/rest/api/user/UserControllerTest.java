package vn.codingt.clean.presenter.rest.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.entities.TestCoreEntityGenerator;
import vn.codingt.clean.core.usecases.user.CreateAUserUseCase;
import vn.codingt.clean.core.util.constant.ApiPath;
import vn.codingt.clean.presenter.rest.api.common.BaseControllerTest;
import vn.codingt.clean.presenter.rest.api.entities.SignInRequest;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;
import vn.codingt.clean.presenter.usecases.UseCaseExecutorImpl;
import vn.codingt.clean.presenter.usecases.secutiry.AuthenticateAUserUseCase;
import vn.codingt.clean.presenter.usecases.secutiry.AuthenticateAUserUseCaseInputMapper;
import vn.codingt.clean.presenter.usecases.secutiry.CreateAUserInputMapper;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest extends BaseControllerTest {

    @Configuration
    @ComponentScan(basePackages = {
            "vn.codingt.clean.presenter.rest.api.user",
            "vn.codingt.clean.presenter.rest.api.common"})
    static class Config {

    }

    private JacksonTester<SignUpRequest> signUpJson;
    private JacksonTester<SignInRequest> signInJson;

    @MockBean
    private AuthenticateAUserUseCase authenticateCustomerUseCase;

    @MockBean
    private CreateAUserUseCase createCustomerUseCase;

    @MockBean
    private CreateAUserInputMapper createCustomerInputMapper;

    @SpyBean
    private UseCaseExecutorImpl useCaseExecutor;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Autowired
    private WebApplicationContext wac;

    @Override
    protected MockMvc getMockMvc() {
        return mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    // Test
    @Test
    public void signInReturnOkWhenAuthenticationWorks() throws Exception {
        // given
        SignInRequest signInRequest = new SignInRequest("email@email.com", "password");

        AuthenticateAUserUseCase.InputValues inputValues = AuthenticateAUserUseCaseInputMapper
                .map(signInRequest);

        String token = "token";

        AuthenticateAUserUseCase.OutputValues output = new AuthenticateAUserUseCase.OutputValues(token);

        String payload = signInJson.write(signInRequest).getJson();
        // and

        doReturn(output)
                .when(authenticateCustomerUseCase)
                .execute(eq(inputValues));

        // when
        RequestBuilder request = asyncPostRequest(ApiPath.API_USER + "/auth", payload);

        // mvcResult.getAsyncResult();
        // then
        this.mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void signInReturnsBadRequestWhenAuthenticationManagerFails() throws Exception {
        // given
        SignInRequest signInRequest = new SignInRequest("emai@email.com", "password");

        String payload = signInJson.write(signInRequest).getJson();
        AuthenticateAUserUseCase.InputValues inputValues = AuthenticateAUserUseCaseInputMapper
                .map(signInRequest);
        // and
        doThrow(new UsernameNotFoundException("Error"))
                .when(authenticateCustomerUseCase)
                .execute(eq(inputValues));

        // when
        RequestBuilder request = asyncPostRequest(ApiPath.API_USER + "/auth", payload);
        // then.
        mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void signUpReturnsBadRequestWhenEmailIsAlreadyBeenUsed() throws Exception {
        // given
        final SignUpRequest signUpRequest = new SignUpRequest("name", "email@email.com", "address", "password");

        String payload = signUpJson.write(signUpRequest).getJson();

        CreateAUserUseCase.InputValues inputValues = new CreateAUserUseCase.InputValues(null, null, null,
                null);
        // and
        doReturn(inputValues)
                .when(createCustomerInputMapper)
                .map(eq(signUpRequest));

        // when
        RequestBuilder requestBuilder = asyncPostRequest(ApiPath.API_USER, payload);

        // then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void signUpReturnsCreateWhenIsANewCustomer() throws Exception {
        // given
        final SignUpRequest signUpRequest = new SignUpRequest("name", "email@email.com", "address", "password");

        String payload = signUpJson.write(signUpRequest).getJson();

        User customer = TestCoreEntityGenerator.randomCustomer();

        CreateAUserUseCase.InputValues input = new CreateAUserUseCase.InputValues(customer.getName(),
                customer.getEmail(), customer.getAddress(), customer.getPassword());

        CreateAUserUseCase.OutputValues outputValues = new CreateAUserUseCase.OutputValues(customer);

        // and
        doReturn(input)
                .when(createCustomerInputMapper)
                .map(eq(signUpRequest));

        doReturn(outputValues)
                .when(createCustomerUseCase)
                .execute(eq(input));

        // when
        RequestBuilder request = asyncPostRequest(ApiPath.API_USER, payload);
        // then

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

}
