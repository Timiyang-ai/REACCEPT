int size() {
    int s = 0;
    for(final DatabaseUpdates c : pendingUpdates.values()) s += c.nodes.size();
    return s;
  }