public static void rename(String src, String dst) throws IOException {
    UnderFileSystem ufs = UnderFileSystem.get(src);
    ufs.rename(src, dst);
    LOG.info("Renamed " + src + " to " + dst);
  }