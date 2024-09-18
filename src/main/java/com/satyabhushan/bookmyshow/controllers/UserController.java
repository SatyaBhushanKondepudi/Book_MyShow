package com.satyabhushan.bookmyshow.controllers;

import com.satyabhushan.bookmyshow.Validations.UserValidations.Emailvalidation;
import com.satyabhushan.bookmyshow.Validations.UserValidations.PasswordValidation;
import com.satyabhushan.bookmyshow.Validations.UserValidations.PhoneNumberValidation;
import com.satyabhushan.bookmyshow.dtos.EditUser.EditUserResponseDto;
import com.satyabhushan.bookmyshow.dtos.ResponseStatus;
import com.satyabhushan.bookmyshow.dtos.SignIn.SignInResponseDto;
import com.satyabhushan.bookmyshow.dtos.SignUp.SignUpRequestDto;
import com.satyabhushan.bookmyshow.dtos.SignUp.SignUpResponseDto;
import com.satyabhushan.bookmyshow.exceptions.User.InvalidCredentialsException;
import com.satyabhushan.bookmyshow.exceptions.User.InvalidInputException;
import com.satyabhushan.bookmyshow.exceptions.User.UserNotFoundException;
import com.satyabhushan.bookmyshow.models.Users.User;
import com.satyabhushan.bookmyshow.models.Users.UserBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.satyabhushan.bookmyshow.services.UserService.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
    /*
    1. SignUp User
    2. SignIn User
    3. Edit UserDetails
     */
    private IUserService userService ;
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signUp/")
    public ResponseEntity<SignUpResponseDto> signUp(
            @RequestBody SignUpRequestDto signUpRequestDto)
            throws Exception{
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        try{
            if(PasswordValidation.isValid(signUpRequestDto.getPassword()) == false){
                throw new InvalidInputException("Invalid password");
            }
            else if(PhoneNumberValidation.isValid(signUpRequestDto.getPhone()) == false){
                throw new InvalidInputException("Invalid phone number");
            }
            else if(Emailvalidation.isValid(signUpRequestDto.getEmail()) == false){
                throw new InvalidInputException("Invalid email");
            }
            User user = new UserBuilder()
                    .setName(signUpRequestDto.getName())
                    .setEmail(signUpRequestDto.getEmail())
                    .setPhone(signUpRequestDto.getPhone())
                    .setPassword(signUpRequestDto.getPassword())
                    .setAddress(signUpRequestDto.getAddress())
                    .setCity(signUpRequestDto.getCity())
                    .setState(signUpRequestDto.getState())
                    .setCountry(signUpRequestDto.getCountry())
                    .setZip(signUpRequestDto.getZip())
                    .setRole(signUpRequestDto.getRole())
                    .build();

            User dbuser = (User) userService.signUp(user);
            signUpResponseDto.setUser(dbuser);
            signUpResponseDto.setStatus(ResponseStatus.SUCCESS);
            signUpResponseDto.setMessage(dbuser.getRole() + " created successfully");
            return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);
        }
        catch (Exception e){
            signUpResponseDto.setStatus(ResponseStatus.FAILURE);
            signUpResponseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(signUpResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signIn/{email}/{password}")
    public ResponseEntity<SignInResponseDto> signIn(@PathVariable String email,
                                                    @PathVariable String password)
    throws InvalidCredentialsException {
        SignInResponseDto signInResponseDto = new SignInResponseDto();
        try{
            User user = (User) userService.signIn(email, password);
            signInResponseDto.setUser(user);
            signInResponseDto.setStatus(ResponseStatus.SUCCESS);
            signInResponseDto.setMessage(user.getRole() + " signed in successfully");
            return new ResponseEntity<>(signInResponseDto, HttpStatus.OK);
        }
        catch (InvalidCredentialsException e){
            signInResponseDto.setStatus(ResponseStatus.FAILURE);
            signInResponseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(signInResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editUser/{email}")
    public ResponseEntity<EditUserResponseDto> editUser(@PathVariable String email,
                                                        @RequestBody SignUpRequestDto editUserRequestDto)
            throws UserNotFoundException, InvalidInputException {
        EditUserResponseDto editUserResponseDto = new EditUserResponseDto();
        try {
            User user = (User) userService.editUser(email, editUserRequestDto);
            editUserResponseDto.setUser(user);
            editUserResponseDto.setStatus(ResponseStatus.SUCCESS);
            editUserResponseDto.setMessage("User edited successfully");
            return new ResponseEntity<>(editUserResponseDto, HttpStatus.OK);
        } catch (InvalidInputException | UserNotFoundException e) {
            editUserResponseDto.setStatus(ResponseStatus.FAILURE);
            editUserResponseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(editUserResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUser/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) throws UserNotFoundException {
        User user = (User) userService.getUser(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
