package com.example.jitendrakumar.expensetracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button signupBtn, loginBtn;
    DatabaseHelper databaseHelper;
    EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signupBtn = (Button) findViewById(R.id.signupBtn);
        loginBtn = (Button) findViewById( R.id.loginBtn );
        etUserName = (EditText)findViewById( R.id.etUserName );
        etPassword = (EditText) findViewById( R.id.etPassword );

        final Intent i = new Intent(MainActivity.this, SignupActivity.class);
         signupBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(i);
             }
         });

       /*
         loginBtn.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Cursor result = databaseHelper.getAllData();
                 String un = etUserName.getText().toString();
                 String pass= etPassword.getText().toString();
             //    String username = result.getString( 1 );
             //    String password = result.getString( 4 );

                 Toast.makeText( MainActivity.this,un+pass+" ",Toast.LENGTH_SHORT ).show();

                 if((result.getCount() == 0))
                 {
                     // Show message
                     Toast.makeText( MainActivity.this, "Nothing in DataBase." , Toast.LENGTH_SHORT).show();
                     if( (un.equals(" ") || pass.equals(" "))){
                         Toast.makeText( MainActivity.this, "Please Fill the Fields First" , Toast.LENGTH_SHORT).show();
                     }

                     return;
                 }
                 else
                 {
                     String username = result.getString( 1 );
                     String password = result.getString( 4 );

                     if(username.equals(un) && password.equals(pass)){
                         Toast.makeText( MainActivity.this,"User is verified", Toast.LENGTH_SHORT ).show();

                    }
                 }

             }
         } );
*/

    }

}
