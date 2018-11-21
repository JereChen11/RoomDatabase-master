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
    private EditText mIdNumberEditText;
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
                mUser.setUid(Integer.parseInt(mIdNumberEditText.getText().toString()));
                mUser.setFirstName(mFirstNameEditText.getText().toString());
                mUser.setLastName(mLastNameEditText.getText().toString());
                mAppDatabase.userDao().insert(mUser);
                Log.d("tag", "");
            }
        });

        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user = mAppDatabase.userDao().findById(1);
                Log.d("tag", "" + mAppDatabase.userDao().getAll());
                mFirstNameTextView.setText(user.getFirstName());
                mLastNameTextView.setText(user.getLastName());
            }
        });

    }

    private void findViewId() {
        mSaveButton = findViewById(R.id.save_button);
        mReadButton = findViewById(R.id.read_button);
        mIdNumberEditText = findViewById(R.id.id_edit);
        mFirstNameEditText = findViewById(R.id.first_name_edit);
        mLastNameEditText = findViewById(R.id.last_name_edit);
        mFirstNameTextView = findViewById(R.id.first_name_text);
        mLastNameTextView = findViewById(R.id.last_name_text);
    }

}
