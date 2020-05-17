package ru.itis.blog.repositories.jpa;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ru.itis.blog.models.Role;
import ru.itis.blog.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //language=SQL
    private static final String SQL_SELECT_BY_CONFIRMCODE =
            "select u from itis_user u where confirm_code = ?1 ";

    //language=SQL
    private static final String SQL_SELECT_BY_ROLE =
            "select u from itis_user u where role = ?1 ";

    //language=SQL
    private static final String SQL_SELECT_ =
            "select u from itis_user u ";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL =
            "select u from itis_user u where email = ?1 ";


    @Transactional
    @Override
    public Optional<User> findByConfirmCode(String token) {
        try {
            User user = (User) entityManager.createQuery(SQL_SELECT_BY_CONFIRMCODE)
                    .setParameter(1, token)
                    .getSingleResult();
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return entityManager.createQuery(SQL_SELECT_).getResultList();
    }

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = entityManager.find(User.class, id);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = (User) entityManager.createQuery(SQL_SELECT_BY_EMAIL)
                    .setParameter(1, email)
                    .getSingleResult();
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByRole(Role role) {
        try {
            User user = (User) entityManager.createQuery(SQL_SELECT_BY_ROLE)
                    .setParameter(1, role)
                    .getSingleResult();
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


}
