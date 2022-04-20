package lt.viko.eif.rcepauskas.soap;

import lt.viko.eif.rcepauskas.blog.Blog;
import lt.viko.eif.rcepauskas.blog.DataService;
import lt.viko.eif.rcepauskas.soap.repositories.PostRepository;
import lt.viko.eif.rcepauskas.soap.repositories.UserRepository;

public class UnitOfWork {
    private Blog blog = DataService.createBlogData();

    public PostRepository posts = new PostRepository(blog);
    public UserRepository users = new UserRepository(blog);
}
