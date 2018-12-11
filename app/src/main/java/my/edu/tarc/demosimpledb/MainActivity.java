package my.edu.tarc.demosimpledb;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String DATABASE_NAME = "user_db";
    private AppDatabase appDatabase;
    private EditText editTextPhone, editTextFirstName, editTextLastName, editTextSearch;
    private Button buttonSave, buttonSearch;
    private TextView textViewName;
    List<User> userList;
    List<User> searchUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextSearch = findViewById(R.id.editTextSearch);

        textViewName = findViewById(R.id.textViewName);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchUser();
            }
        });



        loadUser();
    }

    private void loadUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userList = appDatabase.userDao().loadAllUsers();
                //Toast.makeText(getApplicationContext(), "Row:" + userList.size(), Toast.LENGTH_LONG).show();
            }
        }).start();
    }

    private void searchUser() {
        String phone;

        phone = editTextSearch.getText().toString();
        for (int i=0; i < searchUser.size(); i++){
            if(phone.contentEquals(searchUser.get(i).phone)){
                textViewName.setText(searchUser.get(i).getFirstName());
                break;
            }
        }

    }

    private void saveUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.setPhone(editTextPhone.getText().toString());
                user.setFirstName(editTextFirstName.getText().toString());
                user.setLastName(editTextLastName.getText().toString());
                appDatabase.userDao().insertUser(user);

                searchUser.add(user);

                clearScreen();
            }
        }).start();
    }

    private void clearScreen() {
        editTextPhone.setText("");
        editTextFirstName.setText("");
        editTextLastName.setText("");
    }
}
