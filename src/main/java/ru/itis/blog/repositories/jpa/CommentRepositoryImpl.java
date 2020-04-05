package ru.itis.blog.repositories.jpa;

import org.springframework.stereotype.Component;
import ru.itis.blog.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //language=SQL
    private static final String SQL_SELECT_BY_POST_ID =
            "select c from comment c where post_id = ?1 ";

    @Override
    @Transactional
    public List<Comment> getAllByPost_Id(Long post_id) {
        List<Comment> comments= entityManager.createQuery(SQL_SELECT_BY_POST_ID)
                .setParameter(1,post_id)
                .getResultList();
        return comments;
    }

    @Override
    @Transactional
    public void save(Comment comment) {
        entityManager.persist(comment);
    }
}
