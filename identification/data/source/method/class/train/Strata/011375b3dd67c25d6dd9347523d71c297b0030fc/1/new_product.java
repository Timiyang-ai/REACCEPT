public static boolean parseBoolean(String str) {
    switch (str.toUpperCase(Locale.ENGLISH)) {
      case "TRUE":
      case "T":
      case "YES":
      case "Y":
        return true;
      case "FALSE":
      case "F":
      case "NO":
      case "N":
        return false;
      default:
        throw new IllegalArgumentException(
            "Unknown Boolean value, must be 'True' or 'False' but was '" + str + "'; " +
                "parser is case insensitive and also accepts 'T', 'Yes', 'Y', 'F', 'No' and 'N'");
    }
  }