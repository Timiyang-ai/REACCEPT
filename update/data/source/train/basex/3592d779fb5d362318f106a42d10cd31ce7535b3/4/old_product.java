private static void add(final int n, final boolean flush) throws Exception {
    new Set(MainOptions.AUTOFLUSH, flush).execute(context);

    // Create test database
    final Command cmd = new CreateDB(NAME);
    cmd.execute(context);
    // Add documents
    for(int i = 0; i < n; i++) {
      new Add(Integer.toString(i), "<xml/>").execute(context);
    }
    // Close database
    new DropDB(NAME).execute(context);
  }