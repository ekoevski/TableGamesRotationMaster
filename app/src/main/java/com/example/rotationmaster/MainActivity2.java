package com.example.rotationmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {
    private TextInputEditText etName;
    private TextInputEditText etStartDate;
    private TextInputEditText etStartTime;
    private TextInputEditText etEndTime;
    private TextInputEditText etEndDate;

    private Button btnAdd;
    private EmployeeRepository repo;

    // Main Activity onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Bind Views
        etName = findViewById(R.id.editText5);
        etStartTime = findViewById(R.id.editText4);
        etStartDate = findViewById(R.id.editText6);
        etEndTime = findViewById(R.id.textInputEditText);
        etEndDate = findViewById(R.id.textInputEditText2);
        btnAdd = findViewById(R.id.button);

        repo = new EmployeeRepository(this);

        // We are creating a view
        View.OnClickListener addListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddButton();
            }
        };
        btnAdd.setOnClickListener(addListener);
    }


    // Functions
    private void handleAddButton() {
        // 1. Grab values from the inputs and put into employee struct
        Employee emp = new Employee();
        emp.name = etName.getText().toString().trim();
        emp.startDate = etStartDate.getText().toString().trim();
        emp.startTime = etStartTime.getText().toString().trim();
        emp.endDate = etEndDate.getText().toString().trim();
        emp.endTime = etEndTime.getText().toString().trim();
        

        // 2. Basic Validation
        if (emp.name.isEmpty()) {
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        // 3. Insert in db
        long id = repo.insert(emp);
        if(id != -1) {
            Toast.makeText(this, "ID:" + id + " has been added", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etStartDate.setText("");
            etStartTime.setText("");
            etEndDate.setText("");
            etEndTime.setText("");
        } else {
            Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
        }
    }
}