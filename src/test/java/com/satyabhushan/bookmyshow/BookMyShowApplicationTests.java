package com.satyabhushan.bookmyshow;

import com.satyabhushan.bookmyshow.controllers.Admin.Admin_TheaterManagement;
import com.satyabhushan.bookmyshow.controllers.MovieTheaterController;
import com.satyabhushan.bookmyshow.controllers.UserController;
import com.satyabhushan.bookmyshow.dtos.EditUser.EditUserResponseDto;
import com.satyabhushan.bookmyshow.dtos.ResponseStatus;
import com.satyabhushan.bookmyshow.dtos.SignUp.SignUpRequestDto;
import com.satyabhushan.bookmyshow.dtos.TheaterRequestDto;
import com.satyabhushan.bookmyshow.exceptions.User.InvalidCredentialsException;
import com.satyabhushan.bookmyshow.exceptions.User.InvalidInputException;
import com.satyabhushan.bookmyshow.exceptions.User.UserNotFoundException;
import com.satyabhushan.bookmyshow.models.ENUMS.Genre;
import com.satyabhushan.bookmyshow.models.ENUMS.ShowName;
import com.satyabhushan.bookmyshow.models.ENUMS.UserType;
import com.satyabhushan.bookmyshow.models.Show.Movie;
import com.satyabhushan.bookmyshow.models.Show.Show;
import com.satyabhushan.bookmyshow.models.Theater.Theater;
import com.satyabhushan.bookmyshow.models.Users.User;
import com.satyabhushan.bookmyshow.repositories.IMovieRepository;
import com.satyabhushan.bookmyshow.repositories.IShowRepository;
import com.satyabhushan.bookmyshow.repositories.ITheaterRepository;
import com.satyabhushan.bookmyshow.repositories.IUserRepository;
import com.satyabhushan.bookmyshow.services.Theater.ITheaterService;
import com.satyabhushan.bookmyshow.services.UserService.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
class BookMyShowApplicationTests {
    @Autowired
    private UserController userController;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void contextLoads() {
    }

//    @Test
//    void testCreateUser() throws UserAlreadyExistsException {
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setName("Satya");
//        signUpRequestDto.setEmail("satyabhushan8887@gmail.com");
//        signUpRequestDto.setPhone("1234567890");
//        signUpRequestDto.setPassword("Satya@123");
//        signUpRequestDto.setAddress("Bangalore");
//        signUpRequestDto.setCity("Bangalore");
//        signUpRequestDto.setState("Karnataka");
//        signUpRequestDto.setCountry("India");
//        signUpRequestDto.setZip("560037");
//
//        ResponseEntity<SignUpResponseDto> signUpResponseDto = userController.createUser(signUpRequestDto);
//        System.out.println(signUpResponseDto.getBody().getMessage());
//        System.out.println(signUpResponseDto.getBody().getUser().getEmail());
//
//    }


    @Test
    void SignInTest() throws InvalidCredentialsException {
        String email = "satyabhushan8889@gmail.com";
        String password = "08@Oct&98";
        User signedInUser = userServiceImpl.signIn(email, password);
        if (signedInUser != null) {
            System.out.println(signedInUser.getEmail());
            System.out.println(signedInUser.getPassword());
            System.out.println(signedInUser.getName());
            System.out.println(signedInUser.getId());
        } else {
            System.out.println("Invalid creds");
        }
    }

    /*Testing Edit User
     * 1. Edit User
     * 2. Email Cannot be edited
     * 3. Phone number and password should be valid

     */
    @Test
    void EditUserTest() throws UserNotFoundException, InvalidInputException {
        SignUpRequestDto editUserRequestDto = new SignUpRequestDto();
        editUserRequestDto.setName("Satya");
        editUserRequestDto.setEmail("Satyabhushan@gmail.com");
        editUserRequestDto.setPhone("1234567890");
        editUserRequestDto.setPassword("123445666@Abc");
        User user = userServiceImpl.editUser("satyabhushan8887@gmail.com", editUserRequestDto);
        EditUserResponseDto editUserResponseDto = new EditUserResponseDto();
        editUserResponseDto.setUser(user);
        editUserResponseDto.setStatus(ResponseStatus.SUCCESS);
        editUserResponseDto.setMessage("User edited successfully");
        System.out.println(editUserResponseDto.getMessage());
        System.out.println(editUserResponseDto.getUser().getName());
        System.out.println(editUserResponseDto.getUser().getEmail());
        System.out.println(editUserResponseDto.getUser().getPhone());
        System.out.println(editUserResponseDto.getUser().getPassword());
    }

    @Autowired
    private IUserRepository IUserRepository;

    @Test
    void findByEmail() {
        Optional<User> user = IUserRepository.findByEmail("satyabhushan8887@gmail.com");
        System.out.println(user.get().getName() + "\n" + user.get().getEmail()
                + "\n" + user.get().getPhone() + "\n" + user.get().getPassword());
    }


    @Test
    void findUser() throws UserNotFoundException {
        User user = userServiceImpl.getUser("satyabhushnaKondepudi@gmail.com");
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getPhone());
    }


    @Autowired
    private ITheaterService iTheaterService;

    @Autowired
    private IMovieRepository iMovieRepository;

    @Autowired
    private IShowRepository iShowRepository;

    @Autowired
    private ITheaterRepository iTheaterRepository;

    @Test
    void TestCaseToFillAllTablesWithDummyData() throws Exception {
        for (int i = 1; i <= 10; i++) {
            SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
            signUpRequestDto.setName("User" + i);
            signUpRequestDto.setEmail("user" + i + "@example.com");
            signUpRequestDto.setPhone("123456789" + i);
            signUpRequestDto.setPassword("Password" + i + "@123");
            signUpRequestDto.setAddress("Address" + i);
            signUpRequestDto.setCity("City" + i);
            signUpRequestDto.setState("State" + i);
            signUpRequestDto.setCountry("Country" + i);
            signUpRequestDto.setZip("Zip" + i);
            userController.signUp(signUpRequestDto);
        }

        // Create dummy data for theaters


        for (int i = 1; i <= 5; i++) {

            Theater theater = new Theater();
            theater.setTheaterName("Theater" + i);
            theater.setTheaterLocation("Location" + i);
            theater.setTheaterCity("City" + i);
            theater.setTheaterState("State" + i);
            theater.setTheaterCountry("Country" + i);
            int numberOfScreens = (int) (Math.random() * 10) + 1;

            for (int j = 1; j <= numberOfScreens; j++) {
                theater.setScreenNumber(j);
                for (int K = 1; K <= 5; K++) {
                    Show show = new Show();
                    show.setId((long) (K));
                    show.setShowName(ShowName.randomShowName(K));
                    switch (show.getShowName()) {
                        case MORNING_SHOW:
                            show.setShowDateTime(Timestamp.valueOf(LocalDateTime.now().plusDays(K).withHour(9).withMinute(0)));
                            break;
                        case MATINEE_SHOW:
                            show.setShowDateTime(Timestamp.valueOf(LocalDateTime.now().plusDays(K).withHour(13).withMinute(0)));
                            break;
                        case FIRST_SHOW:
                            show.setShowDateTime(Timestamp.valueOf(LocalDateTime.now().plusDays(K).withHour(17).withMinute(0)));
                            break;
                        case SECOND_SHOW:
                            show.setShowDateTime(Timestamp.valueOf(LocalDateTime.now().plusDays(K).withHour(21).withMinute(0)));
                            break;
                        case BENEFIT_SHOW:
                            show.setShowDateTime(Timestamp.valueOf(LocalDateTime.now().plusDays(K).withHour(1).withMinute(0)));
                            break;
                    }
                    Movie movie = new Movie();
                    movie.setId((long) (i % 5 + 1 + i + j + K));
                    movie.setMovieName("Movie" + i + j + K);
                    movie.setDuration(Duration.ofMinutes(120 + i + j + K));
                    movie.setRating(5.0 - (i + j + K) * 0.1);
                    movie.setReleaseDate(new Date(System.currentTimeMillis() + j + K + (long) (Math.random() * 30 - 15) * 24 * 60 * 60 * 1000));
                    movie.setLanguage("Language" + i + j + K);
                    movie.setGenre(Genre.randomGenre());
                    iMovieRepository.save(movie); // Save movie to database

                    show.setMovie(movie);
                    show.setTheater(theater);
                    iShowRepository.save(show); // Save show to database
                }
                iTheaterRepository.save(theater); // Save theater to database
            }
        }
    }
}
