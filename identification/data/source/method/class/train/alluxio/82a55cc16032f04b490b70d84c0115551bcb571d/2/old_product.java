public boolean wasLogged(String eventString) {
      for (LoggingEvent e : mEvents) {
        if (e.getRenderedMessage().contains(eventString)) {
          return true;
        }
      }
      return false;
    }