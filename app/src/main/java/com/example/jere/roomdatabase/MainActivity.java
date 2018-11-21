package com.example.jere.roomdatabase;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    private EditText mAddressEditText;
    private User mUser;
    private AppDatabase mAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewId();

//        static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//            @Override
//            public void migrate(@NonNull SupportSQLiteDatabase database) {
//                database.execSQL("ALTER TABLE my_badge "
//                        + " ADD COLUMN salutation TEXT");
//                database.execSQL("ALTER TABLE my_badge "
//                        + " ADD COLUMN visitor_type TEXT");
//                database.execSQL("ALTER TABLE my_badge "
//                        + " ADD COLUMN booth_number TEXT");
//                database.execSQL("ALTER TABLE my_badge "
//                        + " ADD COLUMN enable_photo_e_badge INTEGER NOT NULL DEFAULT 0");
//                database.execSQL("ALTER TABLE my_badge "
//                        + " ADD COLUMN photo_verified INTEGER NOT NULL DEFAULT 0");
//                database.execSQL("ALTER TABLE my_badge "
//                        + " ADD COLUMN info_verified INTEGER NOT NULL DEFAULT 0");
//                database.execSQL("ALTER TABLE my_badge "
//                        + " ADD COLUMN e_badge_profile_photo_image BLOB");
//            }
//        };
//
//        public synchronized static HKTDCFairAppDatabase getAppDatabase(Context context) {
//            if (appDatabase == null) {
//                synchronized (LOCK) {
//                    if (appDatabase == null) {
//                        appDatabase = Room.databaseBuilder(context,
//                                HKTDCFairAppDatabase.class, DB_NAME)
//                                .addMigrations(MIGRATION_1_2)
//                                .allowMainThreadQueries().build();
//                    }
//                }
//            }
//            return appDatabase;
//        }

        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
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

}
