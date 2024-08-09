public static boolean matchPlatform(String platformName, String platforms) {
    if (platforms == null || platforms.trim().isEmpty()) {
      return true;
    }
    String[] names = platforms.split("[,;]");
    for (String name : names) {
      if (name.trim().toLowerCase().contains(platformName)) {
        return true;
      }
    }
    return false;
  }