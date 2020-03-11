package com.example.retrofitexample.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofitexample.R;
import com.example.retrofitexample.adapter.RecyclerViewCustomAdapter;
import com.example.retrofitexample.model.User;
import com.example.retrofitexample.network.GetDataService;
import com.example.retrofitexample.network.RetrofitInstanceClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView userListRecyclerView;
    private GetDataService apiService;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchDataFromServer();
    }

    private void fetchDataFromServer() {
        apiService = RetrofitInstanceClient.getRetrofit().create(GetDataService.class);
        Call<List<User>> call = apiService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                // the response is OK
                if (response.code() == 200) {
                    users = response.body();
                    initializeDisplay();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private void initializeDisplay() {
        userListRecyclerView = findViewById(R.id.list_users);
        RecyclerViewCustomAdapter adapter = new RecyclerViewCustomAdapter(this, users);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userListRecyclerView.setAdapter(adapter);
        userListRecyclerView.setLayoutManager(layoutManager);
    }
}
