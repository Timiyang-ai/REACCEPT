private byte[] boundary(final String ct) throws QueryException {
    int i = ct.toLowerCase(Locale.ENGLISH).indexOf(BOUNDARY_IS);
    if(i == -1) throw HC_REQ.get(info, "No separation boundary specified");

    String b = ct.substring(i + BOUNDARY_IS.length());
    if(b.charAt(0) == '"') {
      // if the boundary is enclosed in quotes, strip them
      i = b.lastIndexOf('"');
      b = b.substring(1, i);
    }
    return token(b);
  }