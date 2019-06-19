# -Android-Music-Downloader-and-Player
A music downloader that connects to a remote server, retrieves songs, then plays them.

Objective: To create an Android app that utilizes a low level OS functionality.

Description:  An app that allows a user to access his or her music collection from anywhere in the world. Thousands of songs can be hosted on a server. Browse your song list, then download on demand. You can download only the songs you want at that moment, saving you precious storage space on your phone. Afterwards you can play the songs directly inside the app. The app utilizes socket connections and threading.

/*********************/
Note: This app was built upon features taught in Bill Butterfield's "Connecting to a MySQL Database" and "Making Lists" Android Studio Tutorials. These tutorials can be found here:\
Making Lists: https://www.youtube.com/watch?v=rdGpT1pIJlw\
Connecting to a MySQL Database: https://www.youtube.com/watch?v=bu5Y3uZ6LLM&t=374s
/********************/

Requirements: 
  1. An SQL server to host the song information. (Artist, title, duration, etc.)
  FILLER IMAGE
  Pictured Above: Database table storing the song names and song durations 
  
  2. A socket server host.
  FILLER IMAGE
  Pictured Above: The song server waiting for a connection, connecting, receiving the song request, sending the requested song, then waiting for another client request. 
  
  3. Android phone
  
  /******************************************************************************************************************/
 
App Instructions:
FILLER IMAGE
FILLER IMAGE
FILLER IMAGE
Step 1. The user clicks get data. Their song collection is then retrieved from the database and listed with the song name and song duration. 
FILLER IMAGE
FILLER IMAGE
FILLER IMAGE
Step 2. The user selects a song and it is downloaded to their phone’s song directory (the directory is pictured before and after the song selection showing that the song has been downloaded to the phone) 
FILLER IMAGE
Step 3. The song is automatically loaded into the Music Player of the app. Now the user can play, pause, and stop the song.
