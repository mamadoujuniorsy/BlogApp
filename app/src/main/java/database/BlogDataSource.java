package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BlogDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public BlogDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addBlog(Blog blog) {
        ContentValues values = new ContentValues();
        values.put("title", blog.getTitle());
        values.put("content", blog.getContent());

        database.insert("blogs", null, values);
    }

    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList();
        Cursor cursor = database.query("blogs", null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Blog blog = new Blog();
            blog.setId(cursor.getInt(cursor.getColumnIndex("id")));
            blog.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            blog.setContent(cursor.getString(cursor.getColumnIndex("content")));

            blogs.add(blog);
            cursor.moveToNext();
        }

        cursor.close();
        return blogs;
    }
}

