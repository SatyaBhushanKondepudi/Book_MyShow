package com.satyabhushan.bookmyshow.services.UserService;

import com.satyabhushan.bookmyshow.Validations.UserValidations.Emailvalidation;
import com.satyabhushan.bookmyshow.Validations.UserValidations.PasswordValidation;
import com.satyabhushan.bookmyshow.Validations.UserValidations.PhoneNumberValidation;
import com.satyabhushan.bookmyshow.dtos.SignUp.SignUpRequestDto;
import com.satyabhushan.bookmyshow.exceptions.User.InvalidCredentialsException;
import com.satyabhushan.bookmyshow.exceptions.User.InvalidInputException;
import com.satyabhushan.bookmyshow.exceptions.User.UserNotFoundException;
import com.satyabhushan.bookmyshow.models.ENUMS.UserType;
import com.satyabhushan.bookmyshow.models.Users.User;
import com.satyabhushan.bookmyshow.repositories.IUserRepository;
import com.satyabhushan.bookmyshow.exceptions.User.UserAlreadyExistsException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl<T extends User> implements IUserService<T> {
    private IUserRepository userRepository;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public T signUp(T user) throws UserAlreadyExistsException , InvalidInputException {
        Optional<User> findUserByEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> findUserByPhone = userRepository.findByPhone(user.getPhone());
        if (!PasswordValidation.isValid(user.getPassword())) {
            throw new InvalidInputException("Invalid password");
        } else if (!Emailvalidation.isValid(user.getEmail())) {
            throw new InvalidInputException("Invalid email");
        } else if (!PhoneNumberValidation.isValid(user.getPhone())) {
            throw new InvalidInputException("Invalid phone number");
        } else if (findUserByEmail.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + user.getEmail());
        } else if (findUserByPhone.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with phone: " + user.getPhone());
        } else {
            String userName = user.getEmail().split("@")[0];
            if(user.getRole()==null){
                user.setRole(UserType.USER);
            }
            user.setUserName(userName);
            userRepository.save(user);
            log.info( user.getRole() + " with userName : " + user.getUserName() + " created successfully");
        }
        return user;
    }

    @Override
    public T signIn(String email, String password) throws InvalidCredentialsException {
        Optional<User> findByEmail = userRepository.findByEmail(email);
        User signedInUser = null;
        if (findByEmail.isPresent() && findByEmail.get().getPassword().equals(password)) {
            User user = findByEmail.get();
            signedInUser = user;
        } else {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return (T) signedInUser;
    }

    @Override
    public T editUser(String email, SignUpRequestDto editUserRequestDto) throws UserNotFoundException, InvalidInputException {
        Optional<User> findByEmail = userRepository.findByEmail(email);
        User editedUser = null;
        if (findByEmail.isPresent()) {
            User user = findByEmail.get();

            if (editUserRequestDto.getEmail() != null && editUserRequestDto.getEmail().equals(user.getEmail()) == false) {
                throw new InvalidInputException("Email cannot be edited");
            }
            else if (editUserRequestDto.getPhone() != null || editUserRequestDto.getPassword() != null) {
                if (!PhoneNumberValidation.isValid(editUserRequestDto.getPhone())) {
                    throw new InvalidInputException("Invalid phone number");
                }
                if (!PasswordValidation.isValid(editUserRequestDto.getPassword())) {
                    throw new InvalidInputException("Invalid password");
                }
            }
            else if(editUserRequestDto.getRole() != null){
                throw new InvalidInputException("Role cannot be edited");
            }


            if (editUserRequestDto.getName() != null) {
                user.setName(editUserRequestDto.getName());
            }
            if (editUserRequestDto.getPassword() != null && PasswordValidation.isValid(editUserRequestDto.getPassword())) {
                user.setPassword(editUserRequestDto.getPassword());
            }
            if (editUserRequestDto.getAddress() != null) {
                user.setAddress(editUserRequestDto.getAddress());
            }
            if (editUserRequestDto.getCity() != null) {
                user.setCity(editUserRequestDto.getCity());
            }
            if (editUserRequestDto.getState() != null) {
                user.setState(editUserRequestDto.getState());
            }
            if (editUserRequestDto.getCountry() != null) {
                user.setCountry(editUserRequestDto.getCountry());
            }
            if (editUserRequestDto.getZip() != null) {
                user.setZip(editUserRequestDto.getZip());
            }
            editedUser = userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return (T) editedUser;
    }

    @Override
    public T getUser(String email) throws UserNotFoundException {
        Optional<User> findByEmail = userRepository.findByEmail(email);
        User user = null;
        if (findByEmail.isPresent()) {
            user = findByEmail.get();
        } else {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return (T) user;
    }
}



















/*
@Override
    public T signUp(T user) throws UserAlreadyExistsException {
        User savedUser = null;
        Optional<User> findUserByEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> findUserByPhone = userRepository.findByPhone(user.getPhone());
        if(PasswordValidation.isValid(user.getPassword()) == false){
            throw new UserAlreadyExistsException("Invalid password");
        }
        else if(Emailvalidation.isValid(user.getEmail()) == false){
            throw new UserAlreadyExistsException("Invalid email");
        }
        else if(PhoneNumberValidation.isValid(user.getPhone()) == false){
            throw new UserAlreadyExistsException("Invalid phone number");
        }
        else if(findUserByEmail.isPresent()){
            throw new UserAlreadyExistsException("User already exists with email: " + user.getEmail());
        }
        else if(findUserByPhone.isPresent()){
            throw new UserAlreadyExistsException("User already exists with phone: " + user.getPhone());
        }
        else{
            System.out.println("User created successfully");
           String userName = user.getEmail().split("@")[0];
           user.setUserName(userName);
           savedUser = userRepository.save(user);
        }
        return savedUser;
    }


    @Override
    public T signIn(String email, String password) throws InvalidCredentialsException {
        Optional<User> findByEmail = userRepository.findByEmail(email);
        User signedInUser = null ;
        if(findByEmail.isPresent() && findByEmail.get().getPassword().equals(password)){
            User user = findByEmail.get();
            signedInUser = user;
        }
        else{
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return signedInUser;
    }

    @Override
    public User editUser(String email, SignUpRequestDto editUserRequestDto)
            throws UserNotFoundException, InvalidInputException {
        Optional<User> findByEmail = userRepository.findByEmail(email);
        User editedUser = null;
        if(findByEmail.isPresent()){
            User user = findByEmail.get();

            if(editUserRequestDto.getEmail() != null && editUserRequestDto.getEmail().equals(user.getEmail()) == false){
                throw new InvalidInputException("Email cannot be edited");
            }
            else if( editUserRequestDto.getPhone() != null || editUserRequestDto.getPassword() != null){
                if(PhoneNumberValidation.isValid(editUserRequestDto.getPhone()) == false){
                    throw new InvalidInputException("Invalid phone number");
                }
                if(PasswordValidation.isValid(editUserRequestDto.getPassword()) == false){
                    throw new InvalidInputException("Invalid password");
                }
            }

            if(editUserRequestDto.getName() != null){
                user.setName(editUserRequestDto.getName());
            }
            if(editUserRequestDto.getPassword() != null && PasswordValidation.isValid(editUserRequestDto.getPassword())){
                user.setPassword(editUserRequestDto.getPassword());
            }
            if(editUserRequestDto.getAddress() != null){
                user.setAddress(editUserRequestDto.getAddress());
            }
            if(editUserRequestDto.getCity() != null){
                user.setCity(editUserRequestDto.getCity());
            }
            if(editUserRequestDto.getState() != null){
                user.setState(editUserRequestDto.getState());
            }
            if(editUserRequestDto.getCountry() != null){
                user.setCountry(editUserRequestDto.getCountry());
            }
            if(editUserRequestDto.getZip() != null){
                user.setZip(editUserRequestDto.getZip());
            }
            editedUser = userRepository.save(user);
        }
        else{
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return editedUser;
    }


 */