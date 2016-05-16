package com.example.cadan.myapplication.backend;

import com.example.cadan.myapplication.backend.model.User;
import com.example.cadan.myapplication.backend.response.CountResponse;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

/**
 * Created by cadan on 04/03/2016.
 */

@Api(
        name = "treasureapi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.cadan.example.com",
                ownerName = "backend.myapplication.cadan.example.com",
                packagePath=""
        )
)
public class TreasureHuntApi {

        @ApiMethod(name = "insertUser",path = "user",httpMethod = ApiMethod.HttpMethod.POST)
        public User insertUser(User u){
                OfyService.ofy().save().entity(u).now();
                return u;
        }

        @ApiMethod(name = "getUserById",path = "user/{id}",httpMethod = ApiMethod.HttpMethod.GET)
        public User getUserById(@Named("id") Long id){
               return OfyService.ofy().load().type(User.class).id(id).now();
        }

        @ApiMethod(name = "getAllUsers",path = "user",httpMethod = ApiMethod.HttpMethod.GET)
        public List<User> getAllUsers(){
                return OfyService.ofy().load().type(User.class).list();
        }

        @ApiMethod(name = "getUserByName",path = "user/byName/{name}",httpMethod = ApiMethod.HttpMethod.GET)
        public List<User> getUserByName(@Named("name") String name) {
                return OfyService.ofy().load().type(User.class).filter("fullName", name).list();
        }

        @ApiMethod(name = "deleteUserById",path = "user/{id}",httpMethod = ApiMethod.HttpMethod.DELETE)
        public void deleteUserById(@Named("id") Long id) {
                OfyService.ofy().delete().type(User.class).id(id).now();
        }

}
