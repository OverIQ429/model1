package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.Entity.PostEntity;
import ru.hpclab.hl.module1.model.Post;
import ru.hpclab.hl.module1.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postEntities.stream()
                .map(this::convertToPost)
                .collect(Collectors.toList());
    }

    public Post getPostById(
            PostEntity id) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);
        return optionalPostEntity.map(this::convertToPost).orElse(null);
    }

    public Post savePost(Post post) {
        PostEntity postEntity = convertToPostEntity(post);
        PostEntity savedPostEntity = postRepository.save(postEntity);
        return convertToPost(savedPostEntity);
    }

    public void deletePost(
            PostEntity id) {
        postRepository.deleteById(id);
    }

    public Post updatePost(
            PostEntity id, Post post) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);

        if (optionalPostEntity.isPresent()) {
            PostEntity existingPostEntity = optionalPostEntity.get();
            existingPostEntity.setText(post.getText());
            existingPostEntity.setPublicationDate(post.getPublicationDate());

            PostEntity updatedPostEntity = postRepository.save(existingPostEntity);
            return convertToPost(updatedPostEntity);
        } else {
            return null;
        }
    }

    private Post convertToPost(PostEntity postEntity) {
        Post post = new Post();
        String id = postEntity.getId();
        UUID uuid = UUID.fromString(id);
        post.setIdentifier(uuid);
        post.setText(postEntity.getText());
        post.setPublicationDate(postEntity.getPublicationDate());
//        if (postEntity.getOwner() != null) {
//            post.setOwnerID(postEntity.getOwner().getId());
//        }
        return post;
    }

    private PostEntity convertToPostEntity(Post post) {
        PostEntity postEntity = new PostEntity();
        String id = postEntity.getId();
        postEntity.setId(id);
        postEntity.setText(post.getText());
        postEntity.setPublicationDate(post.getPublicationDate());
        // Вам нужно получить UserEntity по ownerId из User
        // и установить его в postEntity.setOwner()
        // Например:
        // UserEntity owner = userRepository.findById(post.getOwnerId()).orElse(null);
        // postEntity.setOwner(owner);
        return postEntity;
    }
}


//package ru.hpclab.hl.module1.service;
//
//import ru.hpclab.hl.module1.model.Post;
//import ru.hpclab.hl.module1.repository.PostRepository;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.UUID;
//import java.util.Optional;
//@Service
//public class PostService {
//
//    private final PostRepository postRepository;
//
//    public PostService(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
//    public List<Post> getAllPosts() {
//        return postRepository.findAll();
//    }
//
//    public Post getPostById(String id) {
//        Optional<Post> optionalPost = postRepository.findById(UUID.fromString(id)); // Получаем Optional<Post>
//        if (optionalPost.isPresent()) { // Проверяем, есть ли пост
//            return optionalPost.get(); // Получаем Post из Optional
//        } else {
//            // Обрабатываем случай, когда пост не найден
//            // Например, можно выбросить исключение
//            throw new RuntimeException("Post with ID " + id + " not found");
//        }
//    }
//
//    public Post savePost(Post post) {
//        return postRepository.save(post);
//    }
//
//    public void deletePost(String id) {
//        Optional<Post> optionalPost = postRepository.findById(UUID.fromString(id));
//        if (optionalPost.isPresent()) {
//            postRepository.delete(optionalPost.get()); // Удаляем объект Post
//        } else {
//            // Обрабатываем случай, когда пост не найден (например, выбрасываем исключение)
//            throw new RuntimeException("Post with ID " + id + " not found");
//        }
//    }
//
//    public Post updatePost(String id, Post post) {
//        UUID uuid = UUID.fromString(id);
//        Optional<Post> optionalPost = postRepository.findById(uuid);
//
//        if (optionalPost.isPresent()) {
//            Post existingPost = optionalPost.get(); // Получаем существующий пост
//
//            // Обновляем поля существующего поста данными из пришедшего поста
//            existingPost.setText(post.getText()); // Или используйте другие сеттеры для обновления полей
//
//            // Сохраняем обновленный пост обратно в базу данных
//            return postRepository.save(existingPost);
//        } else {
//            // Обрабатываем случай, когда пост не найден
//            throw new RuntimeException("Post with ID " + id + " not found");
//        }
//    }
//}
