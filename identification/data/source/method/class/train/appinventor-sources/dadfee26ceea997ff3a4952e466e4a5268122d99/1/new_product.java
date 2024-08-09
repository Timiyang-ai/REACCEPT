@SimpleFunction(
      description = "An instant in time specified by MM/dd/YYYY hh:mm:ss or MM/dd/YYYY or hh:mm")
  public static Calendar MakeInstant(String from) {
    try {
      return Dates.DateValue(from);
    } catch (IllegalArgumentException e) {
      throw new YailRuntimeError(
          "Argument to MakeInstant should have form MM/dd/YYYY hh:mm:ss, or MM/dd/YYYY or hh:mm",
          "Sorry to be so picky.");
    }
  }