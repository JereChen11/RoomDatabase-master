package com.example.jere.roomdatabase;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author jere
 * @date 2018/11/21
 */
public class MainActivity extends AppCompatActivity {

    private Button mSaveButton;
    private Button mNextButton;
    private EditText mIdNumberEditText;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private User mUser;
    private AppDatabase mAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewId();

        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser = new User();
                mUser.setUid(Integer.parseInt(mIdNumberEditText.getText().toString()));
                mUser.setFirstName(mFirstNameEditText.getText().toString());
                mUser.setLastName(mLastNameEditText.getText().toString());
                mAppDatabase.userDao().insert(mUser);
                Log.d("tag", "");
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(secondIntent);
            }
        });


    }

    private void findViewId() {
        mNextButton = findViewById(R.id.next_button);
        mSaveButton = findViewById(R.id.save_button);
//        mReadButton = findViewById(R.id.read_button);
        mIdNumberEditText = findViewById(R.id.id_edit);
        mFirstNameEditText = findViewById(R.id.first_name_edit);
        mLastNameEditText = findViewById(R.id.last_name_edit);
//        mFirstNameTextView = findViewById(R.id.first_name_text);
//        mLastNameTextView = findViewById(R.id.last_name_text);
    }

}
