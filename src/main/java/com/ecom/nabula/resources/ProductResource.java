package com.ecom.nabula.resources;

import com.ecom.nabula.db.dao.ProductDao;
import com.ecom.nabula.db.entities.Product;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductDao productDao;

    public ProductResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GET
    @UnitOfWork
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Product getById(@PathParam("id") Long id) {
        return productDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @POST
    @UnitOfWork
    public Response create(Product product) {
        Product createdProduct = productDao.create(product);
        return Response.created(URI.create("/products/" + createdProduct.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void deleteById(@PathParam("id") Long id) {
        productDao.deleteById(id);
    }
}
