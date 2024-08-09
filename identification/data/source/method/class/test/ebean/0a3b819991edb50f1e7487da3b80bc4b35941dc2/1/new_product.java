public static boolean matchPlatform(String platformName, String platforms) {
    if (platforms == null || platforms.trim().isEmpty()) {
      return true;
    }

    for (String name : StringHelper.splitNames(platforms)) {
      if (name.toLowerCase().contains(platformName)) {
        return true;
      }
    }
    return false;
  }