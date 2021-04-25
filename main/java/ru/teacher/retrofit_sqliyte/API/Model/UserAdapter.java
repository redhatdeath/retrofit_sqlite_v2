package ru.teacher.retrofit_sqliyte.API.Model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.teacher.retrofit_sqliyte.R;

public class UserAdapter extends ArrayAdapter<User> {
    Context temp;

    public UserAdapter(@NonNull Context context, User[] data) {
        super(context, R.layout.user_adapter_item, data);
        temp = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final User contact = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_adapter_item, null);
        ((ImageView) convertView.findViewById(R.id.picture)).setImageBitmap(BitmapFactory.decodeResource(temp.getResources(), R.raw.pic));
        //((ImageView) convertView.findViewById(R.id.iv)).setImageBitmap(BitmapFactory.decodeResource(temp.getResources(), R.drawable.pic));
        ((TextView) convertView.findViewById(R.id.name)).setText(contact.getName());
        ((TextView) convertView.findViewById(R.id.email)).setText(contact.getEmail());
        ((TextView) convertView.findViewById(R.id.phone)).setText(contact.getPhone());
        return convertView;
    }
}
