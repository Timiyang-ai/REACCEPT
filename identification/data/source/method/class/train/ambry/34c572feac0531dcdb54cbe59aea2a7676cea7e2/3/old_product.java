public static ArrayList<String> splitString(String data, String delimiter) {
    if (data == null) {
      throw new IllegalArgumentException("Passed in string is null ");
    }
    return new ArrayList<>(Arrays.asList(data.split(delimiter)));
  }