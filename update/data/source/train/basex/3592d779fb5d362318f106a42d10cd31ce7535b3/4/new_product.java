private static void add(final int n, final boolean flush) {
    set(MainOptions.AUTOFLUSH, flush);
    // Create test database
    execute(new CreateDB(NAME));
    // Add documents
    for(int i = 0; i < n; i++) execute(new Add(Integer.toString(i), "<xml/>"));
    // Close database
    execute(new DropDB(NAME));
  }