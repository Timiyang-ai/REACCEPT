static public int getColumnIndexByName(Frame fr, String name) {
    String[] names = fr.names();
    return Arrays.asList(names).indexOf(name);
  }