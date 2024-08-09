private void libraries(final String name, final HashSet<String> libs,
      final StringList missing, final StringList obsolete) throws Exception {

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
      if(!sl.remove(l)) missing.add(l);
    }
    for(final String l : sl) {
      if(l.endsWith(".jar")) obsolete.add(l);
    }
  }