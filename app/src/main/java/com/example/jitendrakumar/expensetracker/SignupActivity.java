package com.example.jitendrakumar.expensetracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText name, mobile, email, password, etId;
    Button loginBtn, btnView, back,btnUpdate, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        myDb = new DatabaseHelper( this );

        name = (EditText) findViewById( R.id.name );
        email = (EditText) findViewById( R.id.email );
        mobile = (EditText) findViewById( R.id.mobile );
        password = (EditText) findViewById( R.id.password );
        loginBtn = (Button) findViewById( R.id.loginBtn );
        btnView = (Button) findViewById( R.id.btnView );
        back = (Button) findViewById( R.id.back );
        btnUpdate = (Button) findViewById( R.id.btnUpdate );
        etId = (EditText) findViewById( R.id.etId );
        btnDelete = (Button) findViewById( R.id.btnDelete );

        adddatainDB();
        viewAllData();
        UpdateData();
        DeleteData();


        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent( SignupActivity.this, MainActivity.class );
                startActivity( home );
            }
        } );


    }

    public void UpdateData(){
        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDb.updateData( etId.getText().toString(), name.getText().toString(), email.getText().toString(), mobile.getText().toString(), password.getText().toString() );

                if(isUpdated == true){
                    Toast.makeText( SignupActivity.this, "Data is updated", Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    Toast.makeText( SignupActivity.this, "Data is not updated", Toast.LENGTH_SHORT ).show();
                }
            }

        } );
    }

    public void adddatainDB() {
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData( name.getText().toString(), email.getText().toString(), mobile.getText().toString(), password.getText().toString() );
                if (isInserted == true) {
                    Toast.makeText( SignupActivity.this, "Data Saved to DataBase.", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( SignupActivity.this, "Data is not Saved to DataBase.", Toast.LENGTH_SHORT ).show();
                }
            }
        } );


    }

    public void viewAllData(){
        btnView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0)
                {
                    // Show message
                    showMessage( "Error", "Nothing Found" );

                    return;
                }
                else
                {
                    StringBuffer buffer = new StringBuffer(  );
                    while (res.moveToNext()){
                        buffer.append( "Id : "+ res.getString( 0 )+"\n" );
                        buffer.append( "Username : "+ res.getString( 1 )+"\n" );
                        buffer.append( "Email : "+ res.getString( 2 )+"\n" );
                        buffer.append( "Mobile : "+ res.getString( 3 )+"\n" );
                        buffer.append( "Password : "+ res.getString( 4 )+"\n\n" );
                    }
                    // Show all data
                    showMessage( "Data", buffer.toString() );
                }
            }

        } );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable( true );
        builder.setTitle( title );
        builder.setMessage( Message );
        builder.show();
    }

    public void DeleteData(){
        btnDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = myDb.deleteData( etId.getText().toString() );
                if(deletedRows > 0)
                {
                    Toast.makeText(SignupActivity.this, "Data is Deleted ", Toast.LENGTH_SHORT  ).show();
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Data is not Deleted ", Toast.LENGTH_SHORT  ).show();
                }
            }
        } );
    }
}
