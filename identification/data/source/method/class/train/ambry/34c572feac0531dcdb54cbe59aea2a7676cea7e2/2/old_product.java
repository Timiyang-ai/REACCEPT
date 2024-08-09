public static ArrayList<String> splitString(String data, String delimiter) {
    if (data == null) {
      throw new IllegalArgumentException("Passed in string is null ");
    }
    return Arrays.stream(data.split(delimiter))
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toCollection(ArrayList::new));
  }