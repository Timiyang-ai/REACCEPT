private void libraries(final String name, final HashSet<String> libs) throws Exception {
    final HashSet<String> sl = new HashSet<String>();
    final NewlineInput nli = new NewlineInput(IO.get("etc/" + name));
    try {
      for(String s; (s = nli.readLine()) != null;) {
        for(final String p : s.split(";")) {
          if(p.contains("%LIB%")) sl.add(p.replace("%LIB%/", ""));
        }
      }
    } finally {
      nli.close();
    }

    for(final String l : libs) {
      if(l.contains("basex")) continue;
      assertTrue("Library not found in '" + name + "': " + l, sl.remove(l));
    }
    final StringBuilder sb = new StringBuilder();
    for(final String l : sl) sb.append("\n- ").append(l);
    assertTrue("Libraries superfluous in '" + name + "':" + sb, sl.isEmpty());
  }