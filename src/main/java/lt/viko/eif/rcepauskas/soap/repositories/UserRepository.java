package lt.viko.eif.rcepauskas.soap.repositories;

import lt.viko.eif.rcepauskas.blog.Blog;
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
    public <T> Object get(Integer id) {
        return null;
    }

    @Override
    public <T> List<Object> getAll() {
        return null;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer id) {

    }
}
