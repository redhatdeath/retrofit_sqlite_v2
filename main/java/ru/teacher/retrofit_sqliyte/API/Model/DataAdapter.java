package ru.teacher.retrofit_sqliyte.API.Model;

public class DataAdapter {
    protected User[] dataForAdapter;

    public DataAdapter(WorkWithDB threadDB) {
        if (dataForAdapter == null)
            threadDB.GetAllData();
    }

    public static void updateData(Iterable<User> inputData) {

    }
}