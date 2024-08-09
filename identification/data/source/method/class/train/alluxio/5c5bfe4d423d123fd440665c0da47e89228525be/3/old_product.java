public static void setLocalDirStickyBit(String dir) {
    try {
      // sticky bit is not implemented in PosixFilePermission
      if (dir.startsWith(TachyonURI.SEPARATOR)) {
        Runtime.getRuntime().exec("chmod o+t " + new File(dir).getAbsolutePath());
      }
    } catch (IOException e) {
      LOG.info("Can not set the sticky bit of the direcotry : " + dir);
    }
  }