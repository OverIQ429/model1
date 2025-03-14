package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service; // Добавлено
import ru.hpclab.hl.module1.Entity.UserEntity;
import ru.hpclab.hl.module1.model.User;
import ru.hpclab.hl.module1.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service // Добавлено, чтобы Spring распознал этот класс как сервис
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(this::convertToUser)
                .collect(Collectors.toList());
    }

    public User getUserById(UserEntity id) {;
        return userRepository.findById(id)
                .map(this::convertToUser)
                .orElse(null); // Или выбросьте исключение, если пользователь не найден
    }

    public User saveUser(User user) {
        UserEntity userEntity = convertToUserEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return convertToUser(savedUserEntity);
    }

    public void deleteUser(UserEntity id) {
        userRepository.deleteById(id); // Используйте deleteById, а не delete
    }

    public User updateUser( UserEntity id, User user) {
        UserEntity existingUserEntity = userRepository.findById(id).orElse(null);

        if (existingUserEntity == null) {
            return null; // Или выбросьте исключение, если пользователь не найден
        }

        // Обновляем поля existingUserEntity из user
        existingUserEntity.setFio(user.getFio());
        existingUserEntity.setEmail(user.getEmail());

        UserEntity updatedUserEntity = userRepository.save(existingUserEntity);
        return convertToUser(updatedUserEntity);
    }

    // Helper methods for conversion
    private User convertToUser(UserEntity userEntity) {
        User user = new User();
        user.setIdentifier(userEntity.getId());
        user.setFio(userEntity.getFio());
        user.setEmail(userEntity.getEmail());
        user.setRegistrationDate(userEntity.getRegistrationDate());
        return user;
    }

    private UserEntity convertToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getIdentifier());
        userEntity.setFio(user.getFio());
        userEntity.setEmail(user.getEmail());
        if (user.getRegistrationDate() == null) {
            userEntity.setRegistrationDate(LocalDateTime.now());
        } else {
            userEntity.setRegistrationDate(user.getRegistrationDate());
        }

        return userEntity;
    }
}

//package ru.hpclab.hl.module1.service;
//
//import ru.hpclab.hl.module1.model.User;
//import ru.hpclab.hl.module1.repository.UserRepository;
//
//import java.util.List;
//import java.util.UUID;
//
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User getUserById(String id) {
//        return userRepository.findById(UUID.fromString(id));
//    }
//
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(String id) {
//        userRepository.delete(UUID.fromString(id));
//    }
//
//    public User updateUser(String id, User user) {
//        user.setIdentifier(UUID.fromString(id));
//        return userRepository.put(user);
//    }
//}
