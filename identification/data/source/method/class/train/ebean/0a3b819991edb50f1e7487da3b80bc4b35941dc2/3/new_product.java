public static boolean matchPlatform(String platformName, String platforms) {
    if (platforms == null || platforms.trim().isEmpty()) {
      return true;
    }

    String genericMatch = genericPlatformMatch(platformName);

    for (String name : StringHelper.splitNames(platforms)) {
      if (name.toLowerCase().contains(platformName)) {
        return true;
      } else if (genericMatch != null && genericMatch.equals(name.toLowerCase())) {
        // allow sqlserver ... to match sqlserver17 and sqlserver16 platforms
        return true;
      }
    }
    return false;
  }