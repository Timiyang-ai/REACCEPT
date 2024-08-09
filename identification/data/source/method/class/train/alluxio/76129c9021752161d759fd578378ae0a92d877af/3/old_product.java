@Nullable
  private static TieredIdentity fromScript() {
    // If a script is configured, run the script to get all locality tiers.
    if (!Configuration.containsKey(PropertyKey.LOCALITY_SCRIPT)) {
      return null;
    }
    String script = Configuration.get(PropertyKey.LOCALITY_SCRIPT);
    String identityString;
    try {
      identityString = ShellUtils.execCommand(script);
    } catch (IOException e) {
      throw new RuntimeException(
          String.format("Failed to run script %s: %s", script, e.toString()), e);
    }
    return parseIdentityString(identityString);
  }