public static String getBucketName(AlluxioURI uri) {
    Preconditions.checkState(isObjectStorage(uri.toString()));
    return uri.getAuthority();
  }