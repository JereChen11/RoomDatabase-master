package com.example.jere.roomdatabase;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mSaveButton;
    private Button mReadButton;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private TextView mFirstNameTextView;
    private TextView mLastNameTextView;
    private User mUser;
    private AppDatabase mAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewId();

        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser = new User();
                mUser.setFirstName(mFirstNameEditText.getText().toString());
                mUser.setLastName(mLastNameEditText.getText().toString());
                mAppDatabase.userDao().insertAll();
                Log.d("tag", "");
            }
        });

        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppDatabase.userDao().getAll();
                Log.d("tag", "" + mAppDatabase.userDao().getAll());
                mFirstNameTextView.setText(mUser.getFirstName());
                mLastNameTextView.setText(mUser.getLastName());
            }
        });

    }

    private void findViewId() {
        mSaveButton = findViewById(R.id.save_button);
        mReadButton = findViewById(R.id.read_button);
        mFirstNameEditText = findViewById(R.id.first_name_edit);
        mLastNameEditText = findViewById(R.id.last_name_edit);
        mFirstNameTextView = findViewById(R.id.first_name_text);
        mLastNameTextView = findViewById(R.id.last_name_text);
    }

}
