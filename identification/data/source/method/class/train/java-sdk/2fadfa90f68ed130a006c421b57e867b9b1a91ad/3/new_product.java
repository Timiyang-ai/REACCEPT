public static RequestBuilder delete(HttpUrl url) {
    return new RequestBuilder(HTTPMethod.DELETE, url);
  }