public static RequestBuilder put(String url) {
    return new RequestBuilder(HTTPMethod.PUT, url);
  }