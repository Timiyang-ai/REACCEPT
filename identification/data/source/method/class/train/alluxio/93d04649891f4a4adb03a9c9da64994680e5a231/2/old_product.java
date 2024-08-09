public static String formatPermission(short permission, boolean isFolder) {
    StringBuffer permissionStr = new StringBuffer();
    if (isFolder) {
      permissionStr.append("d");
    } else {
      permissionStr.append("-");
    }
    permissionStr.append(new FileSystemPermission(permission).toString());
    return permissionStr.toString();
  }