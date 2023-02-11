package vn.edu.stu.quanlysanpham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    private void addControls() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void addEvents() {
        String username = "admin";
        String password = "admin";
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, TrangChinhActivity.class);
//                startActivity(intent);
                if(etUsername.getText().toString().equals(username) && etPassword.getText().toString().equals(password)){
                    Intent intent = new Intent(MainActivity.this, TrangChinhActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "User or password failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}