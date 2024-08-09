@Test
  public final void run() throws IOException {
    // test xquery
    IOFile io = new IOFile("test.xq");
    no(new Run(io.path()));
    io.write(token("// li"));
    no(new Run(io.path()));
    ok(new CreateDB(NAME, FILE));
    ok(new Run(io.path()));
    // test command script (1)
    io = new IOFile("test.bxs");
    io.write(token("<info/>"));
    ok(new Run(io.path()));
    // test command script (2)
    io = new IOFile("test.bxs");
    io.write(token("</>"));
    no(new Run(io.path()));
    io.delete();
  }