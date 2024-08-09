@Test
  public final void run() {
    final IOFile io = new IOFile("test.xq");
    no(new Run(io.path()));
    try {
      io.write(token("// li"));
    } catch(final Exception ex) {
      fail(Util.message(ex));
    }
    no(new Run(io.path()));
    ok(new CreateDB(NAME, FILE));
    ok(new Run(io.path()));
    io.delete();
  }