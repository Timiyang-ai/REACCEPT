@SimpleFunction(
      description = "An instant specified by MM/DD/YYYY hh:mm:ss or MM/DD/YYYY or hh:mm")
  public static Calendar MakeInstant(String from) {
    try {
      return Dates.DateValue(from);
    } catch (IllegalArgumentException e) {
      throw new YailRuntimeError(
          "Argument to MakeInstant should have form MM/DD/YYYY hh:mm:ss, or MM/DD/YYYY or hh:mm",
          "Sorry to be so picky.");
    }
  }