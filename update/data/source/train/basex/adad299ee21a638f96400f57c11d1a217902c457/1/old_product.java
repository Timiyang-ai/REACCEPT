public String[] produces() {
    final String accept = req.getHeader("Accept");
    if(accept == null) return new String[0];

    final String[] acc = accept.split("\\s*,\\s*");
    final int as = acc.length;
    for(int a = 0; a < as; a++) {
      if(acc[a].indexOf(';') != -1) acc[a] = acc[a].replaceAll("\\w*;.*", "");
    }
    return acc;
  }