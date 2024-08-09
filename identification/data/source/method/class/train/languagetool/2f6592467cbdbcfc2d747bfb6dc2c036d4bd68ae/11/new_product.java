public void run() {
    System.out.println("Starting server on port " + port + "...");
    daemon = new Daemon(port, this);
    if (daemon.isRunning()) 
      System.out.println("Server started");
    else
      throw new PortBindingException("LanguageTool server could not be started " +
          "on port " + port + ", maybe something else is running on that port already?");
  }