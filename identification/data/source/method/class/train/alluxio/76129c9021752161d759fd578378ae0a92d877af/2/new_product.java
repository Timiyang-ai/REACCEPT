@Nullable
  private static TieredIdentity fromScript() {
    Path script = Paths.get(Configuration.get(PropertyKey.LOCALITY_SCRIPT));
    if (!Files.exists(script)) {
      return null;
    }
    String identityString;
    try {
      identityString = ShellUtils.execCommand(script.toString());
    } catch (IOException e) {
      throw new RuntimeException(
          String.format("Failed to run script %s: %s", script, e.toString()), e);
    }
    try {
      return fromString(identityString);
    } catch (IOException e) {
      throw new RuntimeException(
          String.format("Failed to parse output of running %s: %s", script, e.getMessage()), e);
    }
  }