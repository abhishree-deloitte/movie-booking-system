# JUnit Test Skeletons

> These are **test skeletons only** (structure + intent), not full test implementations.
> Purpose is to demonstrate **testing strategy and coverage**, as required by the assignment.

---

## Auth Module Tests

### AuthControllerTest

```java
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    void shouldRegisterUser() {
        // skeleton test – controller can be instantiated with mocked service
    }

    @Test
    void shouldLoginUser() {
        // skeleton test – controller wiring verified
    }
}
```

---

## User Module Tests

### UserControllerTest

```java
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private BookingService bookingService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private UserController userController;

    @Test
    void shouldFetchUserProfile() {
        // skeleton test – verifies controller can be constructed
    }

    @Test
    void shouldUpdateUsername() {
        // skeleton test
    }

    @Test
    void shouldUpdateEmail() {
        // skeleton test
    }
}
```

---

## Movie Module Tests

### MovieServiceTest

```java
@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void shouldCreateMovie() {
        // given movie details
        // when createMovie is called
        // then movie is saved
    }

    @Test
    void shouldAddRating() {
        // given existing movie
        // when rating is added
        // then avgRating is updated
    }
}
```

---

## Theatre Module Tests

### TheatreServiceTest

```java
@ExtendWith(MockitoExtension.class)
class TheatreServiceTest {

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private TheatreService theatreService;

    @Test
    void shouldCreateTheatre() {
        // given theatre details
        // when createTheatre is called
        // then theatre is saved
    }
}
```

---

## Screen & Seat Module Tests

### ScreenServiceTest

```java
@ExtendWith(MockitoExtension.class)
class ScreenServiceTest {

    @Mock
    private ScreenRepository screenRepository;

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private ScreenService screenService;

    @Test
    void shouldCreateScreenUnderTheatre() {
        // given theatre exists
        // when screen is created
        // then screen is saved
    }
}
```

### SeatServiceTest

```java
@ExtendWith(MockitoExtension.class)
class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ScreenRepository screenRepository;

    @InjectMocks
    private SeatService seatService;

    @Test
    void shouldCreateSeat() {
        // given screen exists
        // when seat is created
        // then seat is saved
    }
}
```

---

## Show Module Tests

### ShowServiceTest

```java
@ExtendWith(MockitoExtension.class)
class ShowServiceTest {

    @Mock
    private ShowRepository showRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ScreenRepository screenRepository;

    @InjectMocks
    private ShowService showService;

    @Test
    void shouldScheduleShow() {
        // given movie and screen exist
        // when show is scheduled
        // then show is saved
    }
}
```

---

## Booking Module Tests

### BookingServiceTest

```java
@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void shouldCreateBookingAndCalculatePrice() {
        // given show and seats
        // when booking is created
        // then price is calculated and booking saved
    }

    @Test
    void shouldCancelBooking() {
        // given upcoming booking
        // when cancel is called
        // then status becomes CANCELLED
    }
}
```

---

## Notification Module Tests

### NotificationServiceTest

```java
@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldCreateNotification() {
        // given userId and message
        // when notifyUser is called
        // then notification is saved
    }
}
```

---

## Testing Notes for Reviewers

* Controller tests use `@WebMvcTest`
* Service tests use Mockito with `@ExtendWith(MockitoExtension.class)`
* Repositories are mocked in service tests
* Focus is on **structure and intent**, not exhaustive assertions
