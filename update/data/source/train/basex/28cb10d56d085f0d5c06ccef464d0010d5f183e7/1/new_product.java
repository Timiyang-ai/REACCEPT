public Uri baseUri() throws QueryException {
    final HTTPContext http = http();
    return Uri.uri(http.req.getRequestURI().replaceAll(http.req.getPathInfo(), ""));
  }