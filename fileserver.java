/*fileserver.java
 * 
 * Description: This class provides the server on the host
 * PC. It will listen for a connection. When a connection is made
 * it will take a song title as input and send that .mp3 file across
 * the connection*/

package mp3Transfer;

//imported libraries to provide socket and data streams
import java.io.*;
import java.net.*;

public class fileserver {
	public static void main(String[] args) throws Exception {
		while (true) {
			try {
				// establish server
				ServerSocket s = new ServerSocket(4333);
				System.out.println("Server started");
				System.out.println("Waiting for a client ...");
				// wait for client
				Socket sr = s.accept();
				System.out.println("Client accepted");
				// get input from client (mp3 song choice)
				DataInputStream in = new DataInputStream(new BufferedInputStream(sr.getInputStream()));
				String mp3Choice = "";
				try {
					mp3Choice = in.readUTF();
					System.out.println("Selected song is: " + mp3Choice);
				} catch (IOException i) {
					System.out.println(i);
				}

				// retrieve requested mp3 file and send to client
				FileInputStream fr = new FileInputStream(
						"C:\\Users\\" + "HostMachine\\Desktop\\Songs\\" + mp3Choice);
				int count;
				byte[] b = new byte[4096];
				OutputStream os = sr.getOutputStream();
				while ((count = fr.read(b)) > 0) {
					os.write(b, 0, count);
				}
				System.out.println("Song Sent succesfully.");
				// close the connection
				try {
					System.out.println("Closing connection...");
					in.close();
					os.close();
					sr.close();
					fr.close();
					s.close();
					System.out.println("Connection closed.");
				} catch (IOException i) {
					System.out.println(i);
				}

			} catch (IOException i) {
				System.out.println(i);
			}

		}
	}
}
