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
      final StringList missing = new StringList();
      final StringList obsolete = new StringList();
      if(n.endsWith(".bat")) libraries(n, libs, missing, obsolete);
      if(!missing.isEmpty()) {
        final StringBuilder sb = new StringBuilder();
        for(final String l : missing) sb.append(";%LIB%").append(l);
        fail("Library not found in '" + n + "':\n" + sb.substring(1));
      }
      if(!obsolete.isEmpty()) {
        final StringBuilder sb = new StringBuilder();
        for(final String l : obsolete) sb.append(";%LIB%").append(l);
        fail("Library obsolete in '" + n + "':\n" + sb.substring(1));
      }
    }
  }