public boolean wasLogged(String pattern) {
    return mAppender.wasLogged(Pattern.compile(".*" + pattern + ".*"));
  }