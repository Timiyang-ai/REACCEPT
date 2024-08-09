public void run() {
    try {
      server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(port), 0);
      server.createContext("/", new LanguageToolHttpHandler());
      System.out.println("Starting server on port " + port + "...");
      server.start();
      System.out.print("Server started");
    } catch (Exception e) {
      throw new PortBindingException(
          "LanguageTool server could not be started " + "on port " + port
          + ", maybe something else is running on that port already?");

    }

  }