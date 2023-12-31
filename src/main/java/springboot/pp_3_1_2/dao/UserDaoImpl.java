package springboot.pp_3_1_2.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import springboot.pp_3_1_2.entity.User;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.contains(getUser(id)) ? getUser(id) : entityManager.merge(getUser(id)));
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        entityManager.merge(updatedUser);
    }
}
