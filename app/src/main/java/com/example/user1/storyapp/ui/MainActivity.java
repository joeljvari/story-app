package com.example.user1.storyapp.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user1.storyapp.R;

public class MainActivity extends AppCompatActivity {
    private EditText nameField;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.nameEditText);
        startButton = findViewById(R.id.startButton);
        // to get the users name from the nameField when the startButton is pressed.
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String name = nameField.getText().toString(); // getText() To get text , Editable string is converted to a regular string by .toString() fn
               // Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();// Toast
                startStory(name);

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        nameField.setText("");
    }

    private void startStory(String name) {
        // we can use the same variable name because it is out of scope
        Intent intent = new Intent(this,StoryActivity.class);//CRL+P to get help,this is used to go to new Activity.
        Resources resources = getResources();
        String key = resources.getString(R.string.key_name);
        intent.putExtra(key , name); //this name value is passed to the next StoryActivity
        startActivity(intent);

    }
}
