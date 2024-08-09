static int findNameStart(String sql, int startPos) {
    while (true) {
      int colonPos = sql.indexOf(colon, startPos);
      if (colonPos > -1) {
        // validate the next character after the colon (ignore postgres cast)
        char c = sql.charAt(colonPos + 1);
        if (c == '_' || Character.isLetterOrDigit(c)) {
          return colonPos;
        } else {
          startPos = colonPos + 2;
          continue;
        }
      }
      return -1;
    }
  }