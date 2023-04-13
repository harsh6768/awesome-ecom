package com.ecom.nabula.resources;

import com.ecom.nabula.db.dao.OrderDao;
import com.ecom.nabula.db.entities.Order;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final OrderDao orderDao;

    public OrderResource(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @POST
    @UnitOfWork
    public Order createOrder(Order order) {
        return orderDao.create(order);
    }

    @GET
    @UnitOfWork
    public List<Order> listOrders() {
        return orderDao.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Order getOrder(@PathParam("id") long id) {
        return orderDao.findById(id);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Order updateOrder(@PathParam("id") long id, Order order) {
        order.setId(id);
        return orderDao.update(order);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void deleteOrder(@PathParam("id") long id) {
        Order order = orderDao.findById(id);
        orderDao.delete(order);
    }
}