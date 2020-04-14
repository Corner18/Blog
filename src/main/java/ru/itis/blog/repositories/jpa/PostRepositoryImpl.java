package ru.itis.blog.repositories.jpa;

import org.springframework.stereotype.Component;
import ru.itis.blog.models.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class PostRepositoryImpl implements PostRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "select p FROM post p ";

    //language=SQL
    private static final String SQL_BY_ID =
            "select p from post p where id = ?1 ";

    @Override
    @Transactional
    public void save(Post post) {
        entityManager.persist(post);
    }

    @Override
    @Transactional
    public List<Post> findAll() {
        return entityManager.createQuery(SQL_SELECT_ALL).getResultList();
    }

    @Override
    @Transactional
    public Post findById(Long id) {
        Post post = entityManager.find(Post.class, id);
        return post;
    }
}
