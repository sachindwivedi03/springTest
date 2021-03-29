package demoApplication.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Component
public class userDaoList {
    private static List<User> users = new ArrayList<>();

    public static int userCount = 3;

    static {
        users.add(new User(1 , "sachin", new Date()));
        users.add(new User(2 , "billu", new Date()));
        users.add(new User(3 , "shivam", new Date()));
    }

    //create method to return all users
    public List<User> findAll() {
        return users;
    }

    //create method to save new user
    public User save(User user) {
        if (user.getId() == null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    //create method to get user by id
    public User findOne(int id) {
        for (User user:users) {
            if (user.getId()==id){
                return user;
            }
        }return null;
     }

    //delete method to delete user by id
    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getId()== id){
                iterator.remove();
                return user;
            }
        }return null;
    }

}
