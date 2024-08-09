  HttpUrl parse(String url) {
    return useGet
        ? HttpUrl.get(url)
        : HttpUrl.parse(url);
  }