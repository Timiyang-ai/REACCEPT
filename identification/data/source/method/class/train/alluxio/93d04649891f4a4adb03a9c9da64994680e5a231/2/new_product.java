public static String formatPermission(short permission, boolean isDirectory) {
    StringBuffer permissionStr = new StringBuffer();
    if (isDirectory) {
      permissionStr.append("d");
    } else {
      permissionStr.append("-");
    }
    permissionStr.append(new FileSystemPermission(permission).toString());
    return permissionStr.toString();
  }