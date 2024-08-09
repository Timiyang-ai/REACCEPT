public void connect(Socket aSocket) throws UnknownHostException, IOException
   {
      socket = aSocket;
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      remoteServerName = getData("name");
   }