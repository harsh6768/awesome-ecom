package com.ecom.nabula.resources;

import com.ecom.nabula.db.dao.CustomerDao;
import com.ecom.nabula.db.entities.Customer;
import com.ecom.nabula.utils.CustomResponse;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerDao customerDAO;

    public CustomerResource(CustomerDao customerDAO) {
        this.customerDAO = customerDAO;
    }

    @POST
    @Path("/register")
    @UnitOfWork
    public Response register(@Valid Customer customer) {
        // Implement the registration logic here
        try{
            customerDAO.create(customer);
            return Response.status(Response.Status.CREATED).build();
        }catch(Exception e){
            System.out.println("Error : "+e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    @POST
    @Path("/login")
    @UnitOfWork
    public CustomResponse login(@Valid Customer customer) {
        // Implement the login logic here
        Optional<Customer> retrievedCustomer = customerDAO.findByEmail(customer.getEmail());
        if (retrievedCustomer.isPresent() && retrievedCustomer.get().getPassword().equals(customer.getPassword())) {
            return CustomResponse.buildSuccessResponse("User Logged In",retrievedCustomer);
      } else {
         return CustomResponse.buildErrorResponse("Login Failed!");
      }
    }
}
