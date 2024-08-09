@Test
  public void libraries() throws Exception {
    final HashSet<String> libs = new HashSet<String>();
    for(final String s : new String[] { "lib" }) {
      for(final IOFile f : new IOFile(s).children()) libs.add(f.name());
    }

    if(libs.isEmpty()) {
      Util.errln("WindowsScripts: no library files found.");
    } else {
      for(final IOFile f : new IOFile("etc").children()) {
        final String n = f.name();
        if(n.endsWith(".bat")) libraries(n, libs);
      }
    }
  }