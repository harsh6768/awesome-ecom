package com.ecom.nabula.resources;

import com.ecom.nabula.db.dao.ProductDao;
import com.ecom.nabula.db.entities.Product;
import com.ecom.nabula.utils.CustomResponse;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductDao productDao;
    @Inject
    public ProductResource(ProductDao productDao){
        this.productDao=productDao;
    }

    @POST
    @Path("/create")
    @UnitOfWork
    public CustomResponse addNewProduct(Product product){
        try{
            productDao.insert(product);
           return CustomResponse.buildSuccessResponse("New Product Added!",product);
        }catch (Exception error){
            return CustomResponse.buildErrorResponse("Failed to insert new Product!");
        }
    }

    @GET
    @UnitOfWork
    public CustomResponse getAllProducts(){
        try{
           List<Product> listOfProducts =productDao.findAll();
           return  CustomResponse.buildSuccessResponse("All Products",listOfProducts);
        }catch(Exception error){
           return CustomResponse.buildErrorResponse("Failed to Fetch Products");
        }
    }

    @GET
    @Path("/{id}/{testId}")
    public CustomResponse getProductById(@PathParam("id") long id,@PathParam("testId") long testId){
        try{
          final Product product= productDao.findOne(id);
          return CustomResponse.buildSuccessResponse("Product Detail!",product);
        }catch (Exception error){
            return CustomResponse.buildErrorResponse("Failed to Fetch Products");
        }
    }


}
