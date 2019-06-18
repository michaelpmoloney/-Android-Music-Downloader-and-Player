/*
* Student: Michael Moloney
* Student ID: 900-85-1985
* Class: CSC 4320 MW 2:30-4:15pm
* Professor: Zhisheng Yan
* Assignment: Project 2
*
* MainActivity.java
*
* Description: This is the first activity
* that the user will interact with when the
* app is loaded.*/

package com.example.MusicApp2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.sql.*;

public class MainActivity extends AppCompatActivity {
    //global variables declared for the mainclass
    ItemAdapter itemAdapter;
    Context thisContext;
    ListView myListView;
    TextView progressTextView;
    Map<String, Double> songsMap = new LinkedHashMap<String, Double>();
    String musicDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //variables are initialized for the mainclass on startup
        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        progressTextView = (TextView) findViewById(R.id.progressTextView);
        thisContext = this;
        musicDirectory = getExternalFilesDir(Environment.DIRECTORY_MUSIC).toString();
        //set the progress textview to an empty string since nothing has happened yet
        progressTextView.setText("");
        /*declare and initialize the get data button on the main screen.
        * When the button is clicked an instance of the get data class is
        * created and executed*/
        Button btn = (Button) findViewById(R.id.getDataButton);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getData retrieveData = new getData();
                retrieveData.execute("");
            }
        });
    }
    /*
    * getData class
    *
    * Description: This class' methods update the progress textview
    * alerting the user that the program is getting the data from the
    * mysql database, and attempts to make a connection. The class extends
    * AsyncTask so that when it attempts to connect to the database
    * it can do so on a thread in the background. After a connection has
    * been made the progress textview is again updated and a clickable song
    * list is generated.*/
    private class getData extends AsyncTask<String,String,String>{
        //declaring and initializing string that will be written into the
        //progress textview
        String msg = "";
        // JDBC driver name and database URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://" +
                DbStrings.DATABASE_URL +"/" +
                DbStrings.DATABASE_NAME;
        //progress textview is updated
        @Override
        protected void onPreExecute(){
            progressTextView.setText("Connecting to database...");
        }
        //this method attempts to connect to the database in the background
        @Override
        protected String doInBackground(String... strings) {

            Connection conn = null;
            Statement stmt = null;
            //try to connect to the database
            try{
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, DbStrings.USERNAME, DbStrings.PASSWORD);
                //after a connection is made the songs are requested from the db
                stmt = conn.createStatement();
                String sql = "SELECT * FROM songs";
                ResultSet rs = stmt.executeQuery(sql);
                //storing the song names and durations into strings and then
                //storing these in a linked hashmap
                while (rs.next()) {
                    String name = rs.getString("name");
                    double duration = rs.getDouble("duration");

                    songsMap.put(name, duration);
                }
                //update the textview message and close the connection
                msg = "Process complete.";
                rs.close();
                stmt.close();
                conn.close();
            //catch potential errors
            } catch (SQLException connError) {
                msg = connError.getMessage();
                connError.printStackTrace();
            } catch (ClassNotFoundException e) {
                msg = "A class not found exception was thrown.";
                e.printStackTrace();
            } finally {
                //ensure statement and connection are closed
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        //after execution
        @Override
        protected void onPostExecute(String msg){
            //udate progress textview
            progressTextView.setText(this.msg);
            /*if songs were retrieved from the database create an instance
            * of the itemAdapter class to populate the listview with the data*/
            if (songsMap.size() > 0) {
                itemAdapter = new ItemAdapter(thisContext, songsMap);
                    myListView.setAdapter(itemAdapter);


               //make list clickable and send the necessary song data to the downloadActivity
                //when it is clicked on
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String Song =(String) itemAdapter.getItem(position);
                        Intent showDetailActivity = new Intent(getApplicationContext(), DownloadActivity.class);
                        showDetailActivity.putExtra("com.example.Song", Song);
                        showDetailActivity.putExtra("com.example.path", musicDirectory);
                        startActivity(showDetailActivity);
                    }
                });
            }

        }

    }

} // End of MainActivity
