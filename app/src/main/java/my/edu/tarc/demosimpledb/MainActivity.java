package my.edu.tarc.demosimpledb;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPhone, editTextFirstName, editTextLastName;
    private Button buttonSave;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewUser);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });

        userViewModel = ViewModelProviders.of(this).get();

        final UserAdapter userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void saveUser() {

    }

    private void clearScreen() {
        editTextPhone.setText("");
        editTextFirstName.setText("");
        editTextLastName.setText("");
    }
}
