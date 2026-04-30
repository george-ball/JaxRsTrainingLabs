package com.example.api.filter;

import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

/**
 * @NameBinding annotation for selective authentication.
 *
 * Apply to a filter AND to a resource class or method to restrict
 * the filter to run only on those annotated endpoints.
 *
 * LAB 4 — used in BearerTokenFilter and SecuredOrderResource/AdminResource.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Secured {}
