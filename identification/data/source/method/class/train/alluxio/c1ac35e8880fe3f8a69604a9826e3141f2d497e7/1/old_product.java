public static String getBucketName(AlluxioURI uri) {
    return uri.getAuthority();
  }