public AlluxioURI join(String suffix) {
    // TODO(gpang): there should be other usage of join() which can use joinUnsafe() instead.
    String path = getPath();
    StringBuilder sb = new StringBuilder(path.length() + 1 + suffix.length());

    return new AlluxioURI(this,
        sb.append(path).append(AlluxioURI.SEPARATOR).append(suffix).toString(), true);
  }