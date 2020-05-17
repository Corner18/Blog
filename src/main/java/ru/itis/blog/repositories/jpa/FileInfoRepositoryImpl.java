package ru.itis.blog.repositories.jpa;


import org.springframework.stereotype.Component;


import ru.itis.blog.models.FileInfo;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.persistence.PersistenceContext;



@Component
public class FileInfoRepositoryImpl implements FileInfoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //language=SQL
    private static final String SQL_SELECT_BY_URL =
            "select f from file_info f where url = ?1 ";


    @Override
    @Transactional
    public void save(FileInfo fileInfo) {
        entityManager.persist(fileInfo);
    }

    @Override
    @Transactional
    public FileInfo findByUrl(String url) {
        return (FileInfo) entityManager.createQuery(SQL_SELECT_BY_URL)
                .setParameter(1, url)
                .getSingleResult();

    }

}
