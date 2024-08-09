public static RequestBuilder post(HttpUrl url) {
    return new RequestBuilder(HTTPMethod.POST, url);
  }