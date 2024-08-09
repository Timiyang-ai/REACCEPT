public ServerList getServers(long fromId, int maxPageSize) {
    List<Instrumented<ServerStats>> serverList
        = new ArrayList<Instrumented<ServerStats>>(maxPageSize);
    Iterator<Instrumented<ServerStats>> iterator = servers.tailMap(fromId).values().iterator();

    while (iterator.hasNext() && serverList.size() < maxPageSize) {
      serverList.add(iterator.next());
    }
    return new ServerList(serverList, !iterator.hasNext());
  }