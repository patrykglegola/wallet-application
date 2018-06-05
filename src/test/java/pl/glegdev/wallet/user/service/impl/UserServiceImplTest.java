package pl.glegdev.wallet.user.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.glegdev.wallet.user.model.User;
import pl.glegdev.wallet.user.model.UserRole;
import pl.glegdev.wallet.user.persistence.UserRepository;

import javax.persistence.EntityExistsException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private final String ENCODED_PASSWORD = "$1$2$3$4$5$encodedPassword";

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    private final String TEST_PASSWORD = "password";
    private final User TEST_USER_1 = User.builder()
            .username("testuser1")
            .password(TEST_PASSWORD)
            .email("test@test.aa")
            .role(UserRole.USER)
            .build();

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository, passwordEncoder);
        when(passwordEncoder.encode(any())).thenReturn(ENCODED_PASSWORD);
    }

    @Test(expected = EntityExistsException.class)
    public void should_not_create_user_if_username_exists() {
        //given
        when(userRepository.findByUsername(any(String.class))).thenReturn(TEST_USER_1);
        //when:
        userService.create(TEST_USER_1);
        //then throw EntityExistException
    }

    @Test(expected = EntityExistsException.class)
    public void should_not_create_user_if_email_exists() {
        //given
        when(userRepository.findByEmail(any(String.class))).thenReturn(TEST_USER_1);
        //when:
        userService.create(TEST_USER_1);
        //then throw EntityExistException
    }

    @Test
    public void should_create_user_if_username_and_email_not_exists() {
        //given
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        when(userRepository.findByUsername(any(String.class))).thenReturn(null);
        when(userRepository.findByEmail(any(String.class))).thenReturn(null);
        //when
        User user = userService.create(TEST_USER_1);
        //then:
        assertNotNull(user);
        assertEquals(user.getUsername(), TEST_USER_1.getUsername());
        assertEquals(user.getEmail(), TEST_USER_1.getEmail());
        assertEquals(user.getRole(), TEST_USER_1.getRole());
    }

    @Test
    public void should_encode_password_before_save() {
        //given
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        //when:
        User savedUser = userService.create(TEST_USER_1);
        //then:
        assertEquals(savedUser.getUsername(), TEST_USER_1.getUsername());
        assertEquals(savedUser.getPassword(), ENCODED_PASSWORD);
        assertNotEquals(savedUser.getPassword(), TEST_PASSWORD);
    }
}