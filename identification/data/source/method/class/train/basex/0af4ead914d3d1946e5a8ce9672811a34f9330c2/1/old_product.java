public synchronized boolean drop(final User user, final String db) {
    if(db == null) {
      if(users.remove(user.name()) == null) return false;
    } else {
      user.remove(db);
    }
    return true;
  }