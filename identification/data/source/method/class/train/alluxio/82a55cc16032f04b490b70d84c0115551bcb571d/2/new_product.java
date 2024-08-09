public synchronized boolean wasLogged(Pattern pattern) {
      for (LoggingEvent e : mEvents) {
        if (pattern.matcher(e.getRenderedMessage()).matches()) {
          return true;
        }
      }
      return false;
    }