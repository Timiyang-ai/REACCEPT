public static String databaseName(String url) {
    int len = url.length();
    int q = url.indexOf('?');
    if (q == -1) {
      q = len;
    }
    int c = url.indexOf(';');
    if (c == -1) {
      c = len;
    }
    int end = Math.min(q, c);
    String clean = url.substring(0, end);
    int start = Math.max(clean.lastIndexOf(':'), clean.lastIndexOf('/'));
    String dbname = clean.substring(start + 1);

    // check parameters
    int pstart = end + 1;
    int pnameStart = pstart;
    int pnameEnd = pstart;
    for (int i = pstart; i < len; i++) {
      char ch = url.charAt(i);
      if (ch == ';' || ch == '&') {
        String key = url.substring(pnameStart, pnameEnd).trim();
        if (key.equalsIgnoreCase("databaseName") || key.equalsIgnoreCase("database")) {
          dbname = url.substring(pnameEnd + 1, i).trim();
          break;
        }
        pnameStart = i + 1;
      } else if (ch == '=') {
        pnameEnd = i;
      }
    }
    return dbname;
  }