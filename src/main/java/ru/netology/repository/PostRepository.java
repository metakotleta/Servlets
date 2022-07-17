package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

// Stub
public class PostRepository {
  private ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

  public List<Post> all() {
    return posts.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
  }

  public Optional<Post> getById(long id) {
    return posts.entrySet().stream().filter(e -> e.getKey() == id).map(Map.Entry::getValue).findFirst();
  }

  public Post save(Post post) throws NotFoundException {
    if (posts.containsKey(post.getId())) {
      posts.put(post.getId(), post);
    } else if (post.getId() == 0) {
      posts.putIfAbsent(Integer.valueOf(posts.size() + 1).longValue(), post.setId(posts.size() + 1));
    } else {
      throw new NotFoundException();
    }
    return post;
  }

  public void removeById(long id) {
  }
}
