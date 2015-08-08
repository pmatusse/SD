import java.io.*;
import java.net.*;

public class GoOutUser implements Runnable {
	private Socket clientSocket;

	private static Object mutex = new Object();
	private static int numberOfClients;

	// //////////////////////////////////////////////////

	public GoOutUser(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	////////////////////////////////////////////////////

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

			synchronized (mutex) {
				++numberOfClients;
				System.out.println("There are " + numberOfClients + " running!");
			}

			String line = "";
			while (!line.equals("QUIT")) {
				line = in.readLine();
				System.out.println("Client " + clientSocket + " said: \n   " + line);

				out.println("You said: " + line);
				out.flush();
			}

			in.close();
			out.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}