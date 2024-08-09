private AdminOperationType getOperationType(RestRequestMetadata restRequestMetadata) {
    String path = restRequestMetadata.getPath();
    path = path.startsWith("/") ? path.substring(1, path.length()) : path;
    try {
      return AdminOperationType.valueOf(path);
    } catch (IllegalArgumentException e) {
      return AdminOperationType.unknown;
    }
  }