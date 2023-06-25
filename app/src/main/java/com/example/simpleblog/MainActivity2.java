package com.example.simpleblog;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import java.util.List;

        import database.Blog;
        import database.BlogDataSource;

public class MainActivity2 extends AppCompatActivity {
        private EditText etTitle, etContent;
        private Button btnAdd,returnBtn;

        private BlogDataSource blogDataSource;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main2);

                etTitle = findViewById(R.id.etTitle);
                etContent = findViewById(R.id.etContent);
                btnAdd = findViewById(R.id.btnAdd);
                returnBtn= findViewById(R.id.returnBtn);

                blogDataSource = new BlogDataSource(this);
                blogDataSource.open();

                btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                String title = etTitle.getText().toString();
                                String content = etContent.getText().toString();
                                Blog blog = new Blog(title, content);
                                blogDataSource.addBlog(blog);
                                etTitle.setText("");
                                etContent.setText("");

                                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                startActivity(intent);
                        }
                });
                returnBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                startActivity(intent);
                        }
                });
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                blogDataSource.close();
        }
}
