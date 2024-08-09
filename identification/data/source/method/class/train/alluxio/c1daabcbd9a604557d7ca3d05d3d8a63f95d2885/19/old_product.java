public static String getFilePath(String path) throws IOException {
    path = validatePath(path);
    if (path.startsWith(Constants.HEADER)) {
      path = path.substring(Constants.HEADER.length());
    } else if (path.startsWith(Constants.HEADER_FT)) {
      path = path.substring(Constants.HEADER_FT.length());
    }
    return path.substring(path.indexOf(AlluxioURI.SEPARATOR));
  }