private boolean consumes(final HTTPContext http) {
    // return true if no type is given
    if(consumes.isEmpty()) return true;
    // return true if no content type is specified by the user
    final String ct = http.contentType();
    if(ct == null) return true;
    // check if any combination matches
    for(final String c : consumes) {
      if(MimeTypes.matches(c, ct)) return true;
    }
    return false;
  }