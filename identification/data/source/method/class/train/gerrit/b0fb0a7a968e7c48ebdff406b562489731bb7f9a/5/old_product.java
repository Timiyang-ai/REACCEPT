public SafeHtmlBuilder append(final CharSequence in) {
    if (in != null) {
      escapeCS(this, in);
    }
    return this;
  }