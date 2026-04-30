package com.example.api.filter;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;

import java.math.BigDecimal;

/**
 * @BeanParam aggregation bean for product list filtering.
 *
 * LAB 3 — Task 3.3
 *
 * This class collects all the query and header parameters needed for the
 * product list endpoint into one object. The @BeanParam annotation on the
 * resource method tells JAX-RS to create an instance of this class and
 * populate all the annotated fields from the HTTP request.
 *
 * BEFORE refactoring (6-parameter method signature):
 *   public List<Product> list(
 *       @QueryParam("category")                      String     category,
 *       @QueryParam("minPrice")                      BigDecimal minPrice,
 *       @QueryParam("maxPrice")                      BigDecimal maxPrice,
 *       @QueryParam("page")    @DefaultValue("0")    int        page,
 *       @QueryParam("size")    @DefaultValue("20")   int        size,
 *       @QueryParam("sort")    @DefaultValue("name") String     sort)
 *
 * AFTER refactoring (1-parameter method signature):
 *   public List<Product> list(@BeanParam ProductFilter filter)
 *
 * LAB 3 Task 3.3:
 *   1. Add the JAX-RS annotations to each field below.
 *   2. Refactor ParamTestResource.listProducts() to use @BeanParam ProductFilter.
 *   3. Verify the API contract (query parameters the client sends) is unchanged.
 *
 * IMPORTANT: The HTTP API does not change when you introduce @BeanParam.
 * The same query string ?category=books&page=1 still works — JAX-RS just
 * populates the bean fields instead of method parameters.
 */
public class ProductFilter {

    // TODO Lab 3 Task 3.3: add @QueryParam("category")
    public String category;

    // TODO Lab 3 Task 3.3: add @QueryParam("minPrice")
    public BigDecimal minPrice;

    // TODO Lab 3 Task 3.3: add @QueryParam("maxPrice")
    public BigDecimal maxPrice;

    // TODO Lab 3 Task 3.3: add @QueryParam("page") @DefaultValue("0")
    public int page;

    // TODO Lab 3 Task 3.3: add @QueryParam("size") @DefaultValue("20")
    public int size;

    // TODO Lab 3 Task 3.3: add @QueryParam("sort") @DefaultValue("name")
    public String sort;

    // TODO Lab 3 Task 3.3: add @HeaderParam("X-Locale") @DefaultValue("en")
    // This shows @BeanParam can mix different @Param annotation types
    public String locale;
}
