public SafeHtmlBuilder append(CharSequence in) {
    if (in != null) {
      escapeCS(this, in);
    }
    return this;
  }