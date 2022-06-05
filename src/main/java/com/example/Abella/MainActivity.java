package com.example.Abella;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_age = findViewById(R.id.edit_age);
        final EditText edit_gender = findViewById(R.id.edit_gender);
        Button btn = findViewById(R.id.btn_submit);
        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v-> {
            Intent intent = new Intent(MainActivity.this, RVActivity.class);
            startActivity(intent);
        });

        DAOEmployee dao = new DAOEmployee();
        Employee emp_edit = (Employee)getIntent().getSerializableExtra("EDIT");
        if(emp_edit !=null) {
            btn.setText("UPDATE");
            edit_name.setText(emp_edit.getName());
            edit_age.setText(emp_edit.getAge());
            edit_gender.setText(emp_edit.getGender());
            btn_open.setVisibility(View.GONE);
        }
        else {
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }
        btn.setOnClickListener(v-> {
            Employee emp = new Employee(edit_name.getText().toString(), edit_age.getText().toString(), edit_gender.getText().toString());
            if(emp_edit==null) {
                dao.add(emp).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", edit_name.getText().toString());
                hashMap.put("age", edit_age.getText().toString());
                hashMap.put("gender", edit_gender.getText().toString());
                dao.update(emp_edit.getKey(), hashMap).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_menu:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}