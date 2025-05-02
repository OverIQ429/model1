package ru.hpclab.hl.module1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hpclab.hl.module1.model.Likes;
import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hpclab.hl.module1.repository.JpaLikesRepository;
import ru.hpclab.hl.module1.repository.JpaPostRepository;
import ru.hpclab.hl.module1.repository.JpaUserRepository;

import java.util.*;
import java.util.stream.Collectors;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;
@Service
public class UserService {

    private final ObservabilityService observabilityService;

    private final JpaUserRepository userRepository;
    private final JpaPostRepository postRepository;
    private final JpaLikesRepository likesRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class); // Исправлена инициализация логгера


    @Autowired
    public UserService( ObservabilityService observabilityService, JpaUserRepository  userRepository, JpaPostRepository postRepository, JpaLikesRepository likesRepository) {
        this.observabilityService = observabilityService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likesRepository = likesRepository;
    }

    @Transactional
    public List<User> getAllUsers() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        List<User> term = userRepository.findAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllUsers");
        return term;
    }

    public User getUserById(UUID id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        User term = userRepository.findById(id).orElse(null);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return term;
    }

    public void clearAllUsers() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        userRepository.deleteAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
    }
    public User saveUser(User user) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        User savedUser = userRepository.save(user);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return savedUser;
    }

    public void deleteUser(String id) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        userRepository.deleteById(UUID.fromString(id));
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
    }

    public User updateUser(String id, User user) {
        this.observabilityService.start(getClass().getSimpleName() + ":create");
        user.setIdentifier(UUID.fromString(id));
        User updatedUser = userRepository.save(user);
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
        return updatedUser;
    }

//    public List<Map<String, Object>> getSelflikesUser() {
//        List<Likes> allLikes = likesRepository.findAll();
//        Map<UUID, Post> postCache = postRepository.findAll().stream()
//                .collect(Collectors.toMap(Post::getIdentifier, post -> post));
//
//        // Мапа для хранения данных о пользователях и их самолайках
//        Map<UUID, Map<String, Object>> userDataMap = new HashMap<>();
//
//        for (Likes like : allLikes) {
//            Post post = postCache.get(like.getPostId());
//            if (post != null && post.getOwner() != null && post.getOwner().equals(like.getUserId())) {
//                // Если пользователь уже есть в мапе, увеличиваем счетчик
//                if (userDataMap.containsKey(like.getUserId())) {
//                    Map<String, Object> userInfo = userDataMap.get(like.getUserId());
//                    userInfo.put("selfLikeCount", (Integer) userInfo.get("selfLikeCount") + 1);
//                }
//                // Иначе добавляем нового пользователя
//                else {
//                    User user = getUserById(like.getUserId());
//                    Map<String, Object> userInfo = new HashMap<>();
//                    userInfo.put("fio", user.getFio());
//                    userInfo.put("userId", like.getUserId());
//                    userInfo.put("selfLikeCount", 1);
//                    userDataMap.put(like.getUserId(), userInfo);
//                }
//            }
//        }
//
//        // Преобразуем в список и сортируем
//        List<Map<String, Object>> result = new ArrayList<>(userDataMap.values());
//        result.sort((a, b) -> ((Integer) b.get("selfLikeCount")).compareTo((Integer) a.get("selfLikeCount")));
//
//        return result;
//    }
}
