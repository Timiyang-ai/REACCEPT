public static List<Cookie> parseAll(HttpUrl url, Headers headers) {
    List<String> cookieStrings = headers.values("Set-Cookie");
    List<Cookie> cookies = null;

    for (int i = 0, size = cookieStrings.size(); i < size; i++) {
      Cookie cookie = Cookie.parse(url, cookieStrings.get(i));
      if (cookie == null) continue;
      if (cookies == null) cookies = new ArrayList<>();
      cookies.add(cookie);
    }

    return cookies != null
        ? Collections.unmodifiableList(cookies)
        : Collections.<Cookie>emptyList();
  }