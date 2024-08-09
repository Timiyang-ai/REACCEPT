private void run(final int clients, final int runs) throws Exception {
    // Create test database
    Command cmd = new CreateDB(DB, INPUT);
    cmd.execute(CONTEXT);

    // Start clients
    final Client[] cl = new Client[clients];
    for(int i = 0; i < clients; ++i) cl[i] = new Client(runs);
    for(final Client c : cl) c.start();
    for(final Client c : cl) c.join();
    // Drop database
    cmd = new DropDB(DB);
    cmd.execute(CONTEXT);
  }