@Nullable
  private static TieredIdentity fromScript() {
    String scriptName = Configuration.get(PropertyKey.LOCALITY_SCRIPT);
    Path script = Paths.get(scriptName);
    if (!Files.exists(script)) {
      URL resource = TieredIdentityFactory.class.getClassLoader().getResource(scriptName);
      if (resource != null) {
        script = Paths.get(resource.getPath());
      } else {
        return null;
      }
    }
    LOG.debug("Found tiered identity script at {}", script);
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