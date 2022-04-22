package lt.viko.eif.rcepauskas.soap.endpoints;

import lt.viko.eif.rcepauskas.soap.ServiceStatus;
import lt.viko.eif.rcepauskas.soap.UnitOfWork;
import lt.viko.eif.rcepauskas.soap.methods.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://localhost/web-service/user";

    private UnitOfWork unitOfWork;

    @Autowired
    public UserEndpoint(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getPost(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(unitOfWork.users.get(request.getId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
    @ResponsePayload
    public GetUsersResponse getUsers(@RequestPayload GetUsersRequest request) {
        GetUsersResponse response = new GetUsersResponse();
        response.getUsers().addAll(unitOfWork.users.getAll());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "insertUserRequest")
    @ResponsePayload
    public InsertUserResponse insertUser(@RequestPayload InsertUserRequest request) {
        unitOfWork.users.insert(request.getUser());

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("201");
        serviceStatus.setMessage("User inserted successfully");

        InsertUserResponse response = new InsertUserResponse();
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
        unitOfWork.users.update(request.getUser());

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("204");
        serviceStatus.setMessage("User updated successfully");

        UpdateUserResponse response = new UpdateUserResponse();
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
        unitOfWork.users.delete(request.getId());

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("204");
        serviceStatus.setMessage("User deleted successfully");

        DeleteUserResponse response = new DeleteUserResponse();
        response.setServiceStatus(serviceStatus);

        return response;
    }
}
