public static RequestBuilder post(String url) {
    return new RequestBuilder(HTTPMethod.POST, url);
  }