package com.example.retrofitexample.network;

import com.example.retrofitexample.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/users")
    Call<List<User>> getUsers();
}
