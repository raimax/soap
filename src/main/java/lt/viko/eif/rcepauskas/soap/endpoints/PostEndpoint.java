package lt.viko.eif.rcepauskas.soap.endpoints;

import lt.viko.eif.rcepauskas.soap.methods.post.DeletePostRequest;
import lt.viko.eif.rcepauskas.soap.methods.post.DeletePostResponse;
import lt.viko.eif.rcepauskas.soap.methods.post.GetPostRequest;
import lt.viko.eif.rcepauskas.soap.methods.post.GetPostResponse;
import lt.viko.eif.rcepauskas.soap.methods.post.GetPostsRequest;
import lt.viko.eif.rcepauskas.soap.methods.post.GetPostsResponse;
import lt.viko.eif.rcepauskas.soap.methods.post.InsertPostRequest;
import lt.viko.eif.rcepauskas.soap.methods.post.InsertPostResponse;
import lt.viko.eif.rcepauskas.soap.ServiceStatus;
import lt.viko.eif.rcepauskas.soap.UnitOfWork;
import lt.viko.eif.rcepauskas.soap.methods.post.UpdatePostRequest;
import lt.viko.eif.rcepauskas.soap.methods.post.UpdatePostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class PostEndpoint {
    private static final String NAMESPACE_URI = "http://localhost/web-service";

    private UnitOfWork unitOfWork;

    @Autowired
    public PostEndpoint(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPostRequest")
    @ResponsePayload
    public GetPostResponse getPost(@RequestPayload GetPostRequest request) {
        GetPostResponse response = new GetPostResponse();
        response.setPost(unitOfWork.posts.get(request.getId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPostsRequest")
    @ResponsePayload
    public GetPostsResponse getPosts(@RequestPayload GetPostsRequest request) {
        GetPostsResponse response = new GetPostsResponse();
        response.getPosts().addAll(unitOfWork.posts.getAll());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "insertPostRequest")
    @ResponsePayload
    public InsertPostResponse insertPost(@RequestPayload InsertPostRequest request) {
        unitOfWork.posts.insert(request.getPost());

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("201");
        serviceStatus.setMessage("Post inserted successfully");

        InsertPostResponse response = new InsertPostResponse();
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePostRequest")
    @ResponsePayload
    public UpdatePostResponse updatePost(@RequestPayload UpdatePostRequest request) {
        unitOfWork.posts.update(request.getPost());

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("204");
        serviceStatus.setMessage("Post updated successfully");

        UpdatePostResponse response = new UpdatePostResponse();
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePostRequest")
    @ResponsePayload
    public DeletePostResponse deletePost(@RequestPayload DeletePostRequest request) {
        unitOfWork.posts.delete(request.getId());

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("204");
        serviceStatus.setMessage("Post deleted successfully");

        DeletePostResponse response = new DeletePostResponse();
        response.setServiceStatus(serviceStatus);

        return response;
    }
}
