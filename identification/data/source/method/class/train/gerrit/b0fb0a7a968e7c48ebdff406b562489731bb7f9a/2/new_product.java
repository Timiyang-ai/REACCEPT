public SafeHtmlBuilder append(Object in) {
    if (in != null) {
      append(in.toString());
    }
    return this;
  }