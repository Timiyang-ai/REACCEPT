public ServerList getServers(long fromId, int maxPageSize) {
    List<InternalInstrumented<ServerStats>> serverList
        = new ArrayList<InternalInstrumented<ServerStats>>(maxPageSize);
    Iterator<InternalInstrumented<ServerStats>> iterator
        = servers.tailMap(fromId).values().iterator();

    while (iterator.hasNext() && serverList.size() < maxPageSize) {
      serverList.add(iterator.next());
    }
    return new ServerList(serverList, !iterator.hasNext());
  }