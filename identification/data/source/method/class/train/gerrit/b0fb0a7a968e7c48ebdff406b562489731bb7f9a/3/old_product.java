public SafeHtmlBuilder append(final SafeHtml in) {
    if (in != null) {
      cb.append(in.asString());
    }
    return this;
  }