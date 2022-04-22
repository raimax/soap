package lt.viko.eif.rcepauskas.soap.repositories;

import lt.viko.eif.rcepauskas.blog.Blog;
import lt.viko.eif.rcepauskas.blog.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostRepository implements IRepository<Post> {
    private Blog blogContext;

    public PostRepository(Blog context) {
        this.blogContext = context;
    }

    @Override
    public Post get(Integer id) {
        return blogContext.getPosts().stream().filter(post -> post.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return blogContext.getPosts();
    }

    @Override
    public void insert(Post post) {
        blogContext.getPosts().add(post);
    }

    @Override
    public void update(Post post) {
        Integer postIndex = blogContext.getPosts().indexOf(post);
        blogContext.getPosts().set(postIndex, post);
    }

    @Override
    public void delete(Integer id) {
        blogContext.getPosts().remove(this.get(id));
    }
}
