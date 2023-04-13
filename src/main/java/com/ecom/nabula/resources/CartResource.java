package com.ecom.nabula.resources;

import com.ecom.nabula.db.dao.CartDao;
import com.ecom.nabula.db.dao.ProductDao;
import com.ecom.nabula.db.entities.Cart;
import com.ecom.nabula.db.entities.Product;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    private final CartDao cartDao;
    private  final ProductDao productDao;

    public CartResource(CartDao cartDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.productDao=productDao;
    }

    @GET
    @Path("/customer/{customerId}")
    @UnitOfWork
    public List<Cart> getCartsByCustomerId(@PathParam("customerId") long customerId) {
        return cartDao.findByCustomerId(customerId);
    }

    // other methods omitted for brevity
    @POST
    @Path("/{cartId}/products/{productId}")
    @UnitOfWork
    public Response addProductToCart(@PathParam("cartId") Long cartId, @PathParam("productId") Long productId) {
        Cart cart = cartDao.findById(cartId);

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Optional<Product> product = productDao.findById(productId);

        if (!product.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        cart.addProduct(product);
        cartDao.update(cart);

        return Response.ok().build();
    }



}
