package ru.teacher.retrofit_sqliyte.API.Model;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;

import java.util.List;

public class WorkWithDB extends Thread {


    protected Iterable<User> inputData, outputData;
    protected static final long timeInterval;
    protected static boolean flagWork, flagGetAllUser, flagInsertUsers;
    private List<User> selectAllUserFromDB;


    private UserDAO userDAO;
    private AppUserDatabase userDatabase;


    static {
        timeInterval = 1000;
        flagWork = false;
        flagGetAllUser = false;
        flagInsertUsers = false;
    }

    public WorkWithDB(Context context) {
        userDatabase = Room.databaseBuilder(context,
                AppUserDatabase.class, "UsersDB").build();
        userDAO = userDatabase.userDao();

    }


    @Override
    public void run() {
        while (true) {
            if (flagWork) {
                if (flagInsertUsers) {
                    Log.d("runDB", "insert new Data, inputData = " + inputData.toString());
                    userDAO.insertAll(inputData);
                    flagInsertUsers = false;
                    inputData = null;
                }
                if (flagGetAllUser) {
                    Log.d("runDB", "get exist Data");
                    outputData = userDAO.getAllOrderByName();
                }
                if (outputData != null) {
                    DataAdapter.updateData(outputData);
                    outputData = null;
                    flagGetAllUser = false;
                }
                if (!flagGetAllUser && !flagInsertUsers) flagWork = false;
            } else {
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void InsertNewData(Iterable<User> inputData) {
        flagWork = true;
        flagInsertUsers = true;
        this.inputData = inputData;
    }

    public void GetAllData() {
        flagWork = true;
        flagGetAllUser = true;
        outputData = null;
    }
}
