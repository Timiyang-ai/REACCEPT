public static void deleteDir(final String path) throws IOException {
    UnderFileSystem ufs = UnderFileSystem.get(path);

    if (ufs.directoryExists(path) && !ufs.delete(path, true)) {
      throw new IOException("Folder " + path + " already exists but can not be deleted.");
    }
  }