package lt.viko.eif.rcepauskas.soap.repositories;

import lt.viko.eif.rcepauskas.blog.Blog;
import lt.viko.eif.rcepauskas.blog.Post;
import lt.viko.eif.rcepauskas.blog.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository implements IRepository<User> {
    private Blog blogContext;

    public UserRepository(Blog blogContext) {
        this.blogContext = blogContext;
    }

    @Override
    public User get(Integer id) {
        return blogContext.getUsers().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return blogContext.getUsers();
    }

    @Override
    public void insert(User user) {
        blogContext.getUsers().add(user);
    }

    @Override
    public void update(User user) {
        User originalUser = this.get(user.getId());
        Integer userIndex = blogContext.getUsers().indexOf(originalUser);
        blogContext.getUsers().set(userIndex, user);
    }

    @Override
    public void delete(Integer id) {
        blogContext.getUsers().remove(this.get(id));
    }
}
