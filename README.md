# -Android-Music-Downloader-and-Player
A music downloader that connects to a remote server, retrieves songs, then plays them.

Objective: To create an Android app that utilizes a low level OS functionality.

Description:  An app that allows a user to access his or her music collection from anywhere in the world. Thousands of songs can be hosted on a server. Browse your song list, then download on demand. You can download only the songs you want at that moment, saving you precious storage space on your phone. Afterwards you can play the songs directly inside the app. The app utilizes socket connections and threading.

Note: This app was built upon features taught in Bill Butterfield's "Connecting to a MySQL Database" and "Making Lists" Android Studio Tutorials. These tutorials can be found here:<br/>
Making Lists: https://www.youtube.com/watch?v=rdGpT1pIJlw\ <br/>
Connecting to a MySQL Database: https://www.youtube.com/watch?v=bu5Y3uZ6LLM&t=374s

Requirements: 
  1. An SQL server to host the song information. (Artist, title, duration, etc.) <br/>
  FILLER IMAGE <br/>
  Pictured Above: Database table storing the song names and song durations 
  
  2. A socket server host. <br/>
  FILLER IMAGE <br/>
  Pictured Above: The song server waiting for a connection, connecting, receiving the song request, sending the requested song, then waiting for another client request. 
  
  3. Android phone
  
  /******************************************************************************************************************/
 
App Instructions: <br/>
![alt text](https://raw.githubusercontent.com/michaelpmoloney/-Android-Music-Downloader-and-Player/master/Activity%201%20no%20action.JPG)
![alt text](https://raw.githubusercontent.com/michaelpmoloney/-Android-Music-Downloader-and-Player/master/Activity%201%20getting%20data.JPG)
![alt text](https://raw.githubusercontent.com/michaelpmoloney/-Android-Music-Downloader-and-Player/master/Activity%201%20got%20data.JPG) <br/>
Step 1. The user clicks get data. Their song collection is then retrieved from the database and listed with the song name and song duration. <br/>
![alt text](https://raw.githubusercontent.com/michaelpmoloney/-Android-Music-Downloader-and-Player/master/Music%20Directory.JPG)
![alt text](https://raw.githubusercontent.com/michaelpmoloney/-Android-Music-Downloader-and-Player/master/Activity%201%20got%20data.JPG)
![alt text](https://raw.githubusercontent.com/michaelpmoloney/-Android-Music-Downloader-and-Player/master/Music%20Directory%20after.JPG) <br/>
Step 2. The user selects a song and it is downloaded to their phone’s song directory (the directory is pictured before and after the song selection showing that the song has been downloaded to the phone) <br/>
![alt text](https://raw.githubusercontent.com/michaelpmoloney/-Android-Music-Downloader-and-Player/master/Music%20player.JPG) <br/>
Step 3. The song is automatically loaded into the Music Player of the app. Now the user can play, pause, and stop the song.
