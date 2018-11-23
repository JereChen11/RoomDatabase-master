package com.example.jere.roomdatabase;

import android.app.Activity;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author jere
 * @date 2018/11/21
 */
public class MainActivity extends AppCompatActivity {

    public static final String DB_NAME = "JERE_ROOM_DATABASE";

    private Button mSaveButton;
    private Button mNextButton;
    private EditText mIdNumberEditText;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mAddressEditText;
    private User mUser;
    private AppDatabase mAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewId();

        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DB_NAME)
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build();

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser = new User();
                mUser.setUid(Integer.parseInt(mIdNumberEditText.getText().toString()));
                mUser.setFirstName(mFirstNameEditText.getText().toString());
                mUser.setLastName(mLastNameEditText.getText().toString());
                mUser.setAddress(mAddressEditText.getText().toString());
                mAppDatabase.userDao().insert(mUser);
                Log.d("tag", "");

                // hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
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
        mIdNumberEditText = findViewById(R.id.id_edit);
        mFirstNameEditText = findViewById(R.id.first_name_edit);
        mLastNameEditText = findViewById(R.id.last_name_edit);
        mAddressEditText = findViewById(R.id.address_edit);
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user "
                    + " ADD COLUMN address TEXT");

        }
    };

}
