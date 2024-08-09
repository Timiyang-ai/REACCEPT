public static RequestBuilder delete(String url) {
    return new RequestBuilder(HTTPMethod.DELETE, url);
  }