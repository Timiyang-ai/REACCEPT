public static RequestBuilder put(HttpUrl url) {
    return new RequestBuilder(HTTPMethod.PUT, url);
  }