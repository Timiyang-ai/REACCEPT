public synchronized List<String> getHosts() { // TODO: Check whether method should be synchronized
    List<String> hlist = new ArrayList<>();
    for (String h : hostRoleCommands.keySet()) {
      hlist.add(h);
    }
    return hlist;
  }