package com.example.simpleblog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import database.Blog;
import database.BlogDataSource;

public class MainActivity extends AppCompatActivity {
    private Button addBlog;
    private BlogDataSource blogDataSource;
    private LinearLayout blogContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBlog = findViewById(R.id.newBlog);
        blogContainer = findViewById(R.id.blogContainer);

        blogDataSource = new BlogDataSource(this);
        blogDataSource.open();

        updateBlogList();
        addBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBlogList();
    }

    private void updateBlogList() {
        blogContainer.removeAllViews(); // Efface les vues existantes pour éviter les duplications
        List<Blog> blogs = blogDataSource.getAllBlogs();

        for (Blog blog : blogs) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int margin = getResources().getDimensionPixelSize(R.dimen.margin);
            layoutParams.setMargins(margin, margin, margin, margin);

            TextView textView = new TextView(this);
            textView.setLayoutParams(layoutParams);
            textView.setText("Title: " + blog.getTitle() + "\nContent: " + blog.getContent());
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setHeight(200);
            textView.setWidth(250);
            textView.setBackgroundColor(getResources().getColor(R.color.white));
            blogContainer.addView(textView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        blogDataSource.close();
    }
}
