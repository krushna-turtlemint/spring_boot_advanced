package com.mrmk.restfulwebservices.user;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static Integer count = 0;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("mrmk", ++count, LocalDate.now().minusYears(30)));
        users.add(new User("mrkm", ++count, LocalDate.now().minusYears(10)));
        users.add(new User("mr.gladiator", ++count, LocalDate.now().minusYears(30)));
    }

    public List<User> fetchUsers() {
        return users;
    }

    public User addUser(User user) {
        user.setId(++count);
        users.add(user);
        return user;
    }

    public User findOne(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);

        User user = users.stream().filter(predicate).findFirst().orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    public boolean deleteOne(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);

        boolean deletedRecord = users.removeIf(predicate);
        if (!deletedRecord) {
            throw new UserNotFoundException("User not found");
        }
        return deletedRecord;
    }


}
