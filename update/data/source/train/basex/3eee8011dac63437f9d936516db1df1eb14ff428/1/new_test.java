@Test
  public void libraries() throws Exception {
    final HashSet<String> libs = new HashSet<String>();
    for(final String s : new String[] { "lib" }) {
      final int l = libs.size();
      for(final IOFile f : new IOFile(s).children()) libs.add(f.name());
      if(l == libs.size())
        Util.errln(Util.name(this) + ": test skipped (no library files found)");
    }

    for(final IOFile f : new IOFile("etc").children()) {
      final String n = f.name();
      if(n.endsWith(".bat") && !libraries(n, libs)) {
        final TokenBuilder tb = new TokenBuilder("set CP=%CP%");
        for(final IOFile l : new IOFile("lib").children()) {
          if(!l.name().contains("basex")) tb.add(";%LIB%/").add(l.name());
        }
        Util.errln(tb.toString());
        fail(n + ": see STDERR output");
      }
    }
  }