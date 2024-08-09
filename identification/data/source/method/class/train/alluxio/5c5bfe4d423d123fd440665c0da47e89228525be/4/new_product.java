public static void setLocalDirStickyBit(String dir) {
    try {
      // Support for sticky bit is platform specific. Check if the path starts with "/" and if so,
      // assume that the host supports the chmod command.
      if (dir.startsWith(TachyonURI.SEPARATOR)) {
        Runtime.getRuntime().exec("chmod +t " + dir);
      }
    } catch (IOException e) {
      LOG.info("Can not set the sticky bit of the directory: {}", dir, e);
    }
  }