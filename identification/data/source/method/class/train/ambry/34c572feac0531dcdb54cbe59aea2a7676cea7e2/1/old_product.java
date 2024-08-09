public static ArrayList<String> splitString(String data, String delimiter) {
    if (data == null) {
      throw new IllegalArgumentException("Passed in string is null ");
    }
    ArrayList<String> toReturn = new ArrayList<String>();
    String[] slices = data.split(delimiter);
    toReturn.addAll(Arrays.asList(slices));
    return toReturn;
  }