public static RequestBuilder get(String url) {
    return new RequestBuilder(HTTPMethod.GET, url);
  }