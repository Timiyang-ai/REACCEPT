public static String getFilePath(String path, AlluxioConfiguration alluxioConf)
      throws IOException {
    path = validatePath(path, alluxioConf);
    if (path.startsWith(Constants.HEADER)) {
      path = path.substring(Constants.HEADER.length());
    } else if (path.startsWith(Constants.HEADER_FT)) {
      path = path.substring(Constants.HEADER_FT.length());
    }
    return path.substring(path.indexOf(AlluxioURI.SEPARATOR));
  }