package ru.itis.blog.repositories.jpa;

import org.springframework.stereotype.Component;
import ru.itis.blog.models.Comment;
import ru.itis.blog.models.Likes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //language=SQL
    private static final String SQL_SELECT_BY_USER_ID =
            "select l from likes l where user_id= ?1 ";

    @Override
    @Transactional
    public List<Likes> getAllByUser_Id(Long user_id) {
        List<Likes> likes= entityManager.createQuery(SQL_SELECT_BY_USER_ID)
                .setParameter(1,user_id)
                .getResultList();
        return likes;
    }

    @Override
    @Transactional
    public void save(Likes like) {
        entityManager.persist(like);
    }
}
