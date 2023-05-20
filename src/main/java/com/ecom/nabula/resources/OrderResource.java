package com.ecom.nabula.resources;

import com.ecom.nabula.db.dao.OrderDao;
import com.ecom.nabula.db.entities.Order;
import com.ecom.nabula.utils.CustomResponse;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.persistence.GeneratedValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    final OrderDao orderDao;
    @Inject
    public OrderResource(OrderDao orderDao){
        this.orderDao=orderDao;
    }

    @POST
    @UnitOfWork
    public CustomResponse createOrder(Order order){
        try{
            String dateString = "2023-04-18"; // Date in "yyyy-MM-dd" format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = dateFormat.parse(dateString);
            order.setOrderDate(orderDate);

            orderDao.insert(order);
            return CustomResponse.buildSuccessResponse("Order created!",order);
        }catch (Exception error){
            System.out.println("create-order-error:::->"+error);
            return CustomResponse.buildErrorResponse(error.toString());
        }
    }

    @GET
    @UnitOfWork
    public CustomResponse getAllOrders(){
        try{
            List<Order> orderList= orderDao.findAll();
            return CustomResponse.buildSuccessResponse("Orders List",orderList);
        }catch (Exception error){
            System.out.println("get-all-order-error:::->"+error);
            return CustomResponse.buildErrorResponse("Failed to get Order List");
        }
    }

    @GET
    @Path("/{orderId}")
    @UnitOfWork
    public CustomResponse getOrderById(@PathParam("orderId") long orderId){
        try{
            Order order=orderDao.findOne(orderId);
            return CustomResponse.buildSuccessResponse("Order Detail!",order);
        }catch (Exception error){
            return CustomResponse.buildErrorResponse("Failed To get Order Details!");
        }
    }

    @DELETE
    @Path("/{orderId}")
    public CustomResponse deleteOrderById(@PathParam("orderId") long orderId){
        try{
            return CustomResponse.buildSuccessResponse("Order deleted!",null);
        }catch (Exception error){
            return CustomResponse.buildErrorResponse("Failed to delete order!");
        }
    }


}
