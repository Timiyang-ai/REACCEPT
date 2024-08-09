public synchronized void create(final String name, final String password, final Perm perm) {
    users.put(name, new User(name, password, perm == null ? Perm.NONE : perm));
  }