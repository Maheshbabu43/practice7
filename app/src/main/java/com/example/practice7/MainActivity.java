package com.example.practice7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Button button,button2,button3,button4;
    TextView textView,textView1,textView2;
    private static String ip = "ec2-63-32-7-190.eu-west-1.compute.amazonaws.com";
    private static String port = "5432";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "d7enc6mtg3subr";
    private static String username = "igjxxxhsupithb";
    private static String password = "8063d1e97bef96b6d44537d39f50a12f54c874c104fb19bb8c4c0e55d5fffb2d";
    private static String url =  "jdbc:postgresql://"+ip+":"+port+"/"+database;
    private Connection connection = null;
    private Statement statement=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView2);
        textView2=findViewById(R.id.textView3);

    }
    public void start(View view) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Class fail", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Connected no", Toast.LENGTH_SHORT).show();
        }
    }
    public void create(View view)
    {
        try {
            String query="create table student(s_id varchar(10) primary key,s_name varchar(10),s_section varchar(10))";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            Toast.makeText(MainActivity.this, "created", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void insert(View view)
    {
        try {
            String query="insert into student(s_id,s_name,s_section) values('12','naresh1','acp')";
            statement=connection.createStatement();
            statement.executeUpdate(query);
            Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void show(View view)
    {
        try {
            String query="select s_name from student";
            statement=connection.createStatement();
            ResultSet res=null;
            res= statement.executeQuery(query);
            while(res.next())
            {
                String id=res.getString("s_name");
                textView.setText(id);
            }
            res.close();
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}