public synchronized boolean drop(final User user) {
    return users.remove(user.name()) != null;
  }