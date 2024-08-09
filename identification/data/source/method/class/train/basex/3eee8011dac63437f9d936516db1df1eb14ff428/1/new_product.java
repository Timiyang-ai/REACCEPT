private boolean libraries(final String name, final HashSet<String> libs)
      throws Exception {

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

    final StringList mis = new StringList();
    for(final String l : libs) {
      if(!l.contains("basex") && !sl.remove(l)) mis.add(l);
    }
    final StringList obs = new StringList();
    for(final String l : sl) {
      if(l.endsWith(".jar")) obs.add(l);
    }
    if(!mis.isEmpty()) Util.errln("Missing: " + Arrays.toString(mis.toArray()));
    if(!obs.isEmpty()) Util.errln("Obsolete: " + Arrays.toString(obs.toArray()));
    return mis.isEmpty() && obs.isEmpty();
  }