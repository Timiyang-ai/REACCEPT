private boolean produces(final HTTPContext http) {
    // return true if no type is given
    if(produces.isEmpty()) return true;
    // check if any combination matches
    for(final HTTPAccept accept : http.accepts()) {
      for(final String produce : produces) {
        if(MimeTypes.matches(produce, accept.type)) return true;
      }
    }
    return false;
  }