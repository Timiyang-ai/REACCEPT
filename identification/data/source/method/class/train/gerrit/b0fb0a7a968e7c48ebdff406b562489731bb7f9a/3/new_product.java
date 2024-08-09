public SafeHtmlBuilder append(SafeHtml in) {
    if (in != null) {
      cb.append(in.asString());
    }
    return this;
  }