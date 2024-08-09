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
        final StringList missing = new StringList();
        final StringList obsolete = new StringList();
        if(n.endsWith(".bat")) libraries(n, libs, missing, obsolete);
        if(!missing.isEmpty()) {
          final StringBuilder sb = new StringBuilder();
          for(final String l : missing) sb.append(';').append(l);
          fail("Library not found in '" + n + "':\n" + sb.substring(1));
        }
        if(!obsolete.isEmpty()) {
          final StringBuilder sb = new StringBuilder();
          for(final String l : obsolete) sb.append(';').append(l);
          fail("Library obsolete in '" + n + "':\n" + sb.substring(1));
        }
      }
    }
  }