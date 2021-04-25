package ru.teacher.retrofit_sqliyte;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.room.Room;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.teacher.retrofit_sqliyte.API.APIConfig;
import ru.teacher.retrofit_sqliyte.API.APIServiceConstructor;
import ru.teacher.retrofit_sqliyte.API.APIServise;
import ru.teacher.retrofit_sqliyte.API.Model.*;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private User[] data = null;
    private APIServise service;
    private View layout;
    protected WorkWithDB threadDB;
    private UserAdapter userAdapter;
    private DataAdapter dataAdapter;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        threadDB = new WorkWithDB(this);
        threadDB.start();

        dataAdapter = new DataAdapter(threadDB);
        layout = findViewById(R.id.layout);


//        userAdapter = new UserAdapter(getApplicationContext(), data);
//        lv = findViewById(R.id.listView);
//        lv.setAdapter(userAdapter);

        service = APIServiceConstructor.CreateService(APIServise.class);


        Call<ResponseData> call = service.getData();
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body() != null) {
                    threadDB.InsertNewData(response.body().getUsers());
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Snackbar.make(layout, "something do wrong", Snackbar.LENGTH_LONG).show();
                Log.e("getData/Call", t.toString());
            }
        });
    }
}

