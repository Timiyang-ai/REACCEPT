public static TieredIdentity fromScript() {
    String script = Configuration.get(PropertyKey.LOCALITY_SCRIPT);
    String identityString;
    try {
      identityString = ShellUtils.execCommand(script);
    } catch (IOException e) {
      throw new RuntimeException(
          String.format("Failed to run script %s: %s", script, e.toString(), e));
    }
    TieredIdentity identity = parseIdentityString(identityString);
    if (identity == null) {
      throw new RuntimeException(String.format(
          "Failed to parse the output of running %s. "
              + "The value should be a comma-separated list of key=value pairs, but was %s",
          script, identityString));
    }
    return identity;
  }