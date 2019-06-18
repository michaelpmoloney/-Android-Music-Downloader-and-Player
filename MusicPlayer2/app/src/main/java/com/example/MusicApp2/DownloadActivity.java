/*
* DownloadActivity.java
*
* Description: This class connects to the server through
* a socket and downloads the song file and also provides the
* media player function to play, pause, and stop the song*/

package com.example.MusicApp2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MusicApp2.R;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class DownloadActivity extends AppCompatActivity {
    //declare variables
    String song;
    String musicDirectory;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_detail);
        //get item passed during onclick (in this case song title)
        Intent in = getIntent();
        song = in.getStringExtra("com.example.Song");
        song = song+".mp3";
        //get item passed during onclick (in this case phone music directory)
        musicDirectory = in.getStringExtra("com.example.path");
        //display song title in textview
        TextView txt = (TextView) findViewById(R.id.songTitleTextView);
        txt.setText(song+" has been downloaded");
        //start download
        Download dl = new Download(song, musicDirectory);
        dl.execute();
    }
    //music player methods
    public void play(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, Uri.parse(musicDirectory+"//"+song));
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    public void pause(View v) {
        if (player != null) {
            player.pause();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

}
//download song (uses AsyncTask to download song as a
//background thread
class Download extends AsyncTask<String, Void, Void> {

    Socket s;
    String mp3Choice;
    String musicDirectory;

    protected Download(String mp3, String path){
        mp3Choice = mp3;
        musicDirectory = path;
    }

    protected Void doInBackground(String... voids) {

    try {
        //connect to host
        s = new Socket("192.168.56.1",4333);
        // sends song request to the server
        DataOutputStream os = new DataOutputStream(s.getOutputStream());
        os.writeUTF(mp3Choice);
        // gets requested mp3 from server
        int count;
        byte[] b = new byte[4096];
        InputStream is = s.getInputStream();
        FileOutputStream fr = new FileOutputStream(musicDirectory+"//"+mp3Choice);
        while ((count = is.read(b)) > 0) {
            fr.write(b, 0, count);
        }
        //close socket
        s.close();
        is.close();
        fr.close();
        os.close();

    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;
}

}