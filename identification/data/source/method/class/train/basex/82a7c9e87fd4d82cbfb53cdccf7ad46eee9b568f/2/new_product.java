public synchronized User[] users(final Users users) {
    final ArrayList<User> al = new ArrayList<>();
    for(final User user : list) {
      if(users == null || users.get(user.name()) != null) al.add(user);
    }
    return al.toArray(new User[al.size()]);
  }