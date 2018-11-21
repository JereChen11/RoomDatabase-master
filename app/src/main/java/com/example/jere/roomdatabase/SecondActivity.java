package com.example.jere.roomdatabase;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author jere
 * @date 2018/11/21
 */
public class SecondActivity extends AppCompatActivity {

    private Button mReadButton;
    private EditText mInputSearchIdEditText;
    private TextView mFirstNameTextView;
    private TextView mLastNameTextView;
    private AppDatabase mAppDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        findViewId();

        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();

        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user = mAppDatabase.userDao().findById(Integer.parseInt(mInputSearchIdEditText.getText().toString()));
                Log.d("tag", "" + mAppDatabase.userDao().getAll());
                mFirstNameTextView.setText(user.getFirstName());
                mLastNameTextView.setText(user.getLastName());
            }
        });
    }

    private void findViewId() {
        mReadButton = findViewById(R.id.read_button);
        mInputSearchIdEditText = findViewById(R.id.input_search_id_edit_text);
        mFirstNameTextView = findViewById(R.id.first_name_text);
        mLastNameTextView = findViewById(R.id.last_name_text);
    }
}
