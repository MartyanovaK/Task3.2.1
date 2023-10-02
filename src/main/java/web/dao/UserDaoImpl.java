package web.dao;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("from User",User.class).getResultList();

    }




    @Override
    public void add(User user) {

        entityManager.persist(user);
    }


    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class,  id);
        entityManager.remove(user);
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class,  id);
    }
}
