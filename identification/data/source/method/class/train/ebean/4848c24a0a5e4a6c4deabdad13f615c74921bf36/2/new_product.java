public List<String> pendingDrops() {

    List<String> versions = new ArrayList<String>();
    for (Entry value : map.values()) {
      if (value.hasPendingDrops()) {
        versions.add(value.version.asString());
      }
    }
    return versions;
  }