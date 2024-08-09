public static void checkPermission(String user, List<String> groups, FileSystemAction action,
      TachyonURI path, List<FileInfo> fileInfoList) throws AccessControlException,
      InvalidPathException {
    String[] pathComponents = PathUtils.getPathComponents(path.getPath());

    for (int i = fileInfoList.size(); i < pathComponents.length; i ++) {
      fileInfoList.add(null);
    }
    checkByFileInfoList(user, groups, action, path.getPath(), fileInfoList, false);
  }