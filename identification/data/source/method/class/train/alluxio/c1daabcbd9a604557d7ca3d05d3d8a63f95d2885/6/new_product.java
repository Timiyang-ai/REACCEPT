public static String getFilePath(String path, AlluxioConfiguration alluxioConf)
      throws IOException {
    path = validatePath(path, alluxioConf);
    if (path.startsWith(Constants.HEADER)) {
      path = path.substring(Constants.HEADER.length());
    }
    return path.substring(path.indexOf(AlluxioURI.SEPARATOR));
  }