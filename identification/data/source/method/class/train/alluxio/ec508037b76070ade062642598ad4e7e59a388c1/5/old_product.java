public AlluxioURI join(String suffix) {
    return new AlluxioURI(getScheme(), getAuthority(), getPath() + AlluxioURI.SEPARATOR + suffix);
  }