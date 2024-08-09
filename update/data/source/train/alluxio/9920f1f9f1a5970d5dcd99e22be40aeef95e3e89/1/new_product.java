public static void rename(String src, String dst, MasterInfo info) throws IOException {
    UnderFileSystem ufs = UnderFileSystem.get(src, info.getTachyonConf());
    ufs.rename(src, dst);
    LOG.info("Renamed " + src + " to " + dst);
  }