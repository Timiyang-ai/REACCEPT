public void connect() throws UnknownHostException, IOException
    {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        remoteServerName = getData("name");
    }