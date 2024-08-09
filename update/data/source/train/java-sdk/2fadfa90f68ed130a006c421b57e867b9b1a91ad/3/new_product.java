public static RequestBuilder get(HttpUrl url) {
    return new RequestBuilder(HTTPMethod.GET, url);
  }