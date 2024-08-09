static public int getColumnIndexByName(Frame fr, String name) {
    String[] names = fr.names();

    int foundIndex = Arrays.asList(names).indexOf(name);
    if (foundIndex == -1) {
      throw new IllegalArgumentException(String.format("Column with name `%s` was not found in the Frame with key: %s", name,  fr._key));
    }
    return foundIndex;
  }