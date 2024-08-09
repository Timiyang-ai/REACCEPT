public void run() {
    System.out.println("Starting server on port " + port + "...");
    daemon = new Daemon(port, this);
    if (daemon.isRunning()) 
      System.out.println("Server started");
    else
      throw new RuntimeException("Server could not be started");
  }