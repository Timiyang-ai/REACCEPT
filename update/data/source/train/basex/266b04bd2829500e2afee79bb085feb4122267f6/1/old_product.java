public synchronized boolean drop(final User user, final String pattern) {
    if(pattern.isEmpty()) {
      if(users.remove(user.name()) == null) return false;
    } else {
      user.remove(pattern);
    }
    return true;
  }