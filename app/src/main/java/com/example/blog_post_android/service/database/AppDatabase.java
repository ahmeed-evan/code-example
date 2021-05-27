package com.example.blog_post_android.service.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.blog_post_android.service.dao.PostDao;
import com.example.blog_post_android.service.model.Post;

@Database(version = 1, entities = {Post.class})
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase databaseInstance;
    public static String DB_NAME = "app_database";

    public abstract PostDao postDao();

    public static synchronized AppDatabase getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class
                    , DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return databaseInstance;
    }

  Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(databaseInstance);
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private PostDao postDao;

        private PopulateAsyncTask(AppDatabase database) {
            this.postDao = database.postDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            postDao.deleteAll();
            return null;
        }
    }

}
