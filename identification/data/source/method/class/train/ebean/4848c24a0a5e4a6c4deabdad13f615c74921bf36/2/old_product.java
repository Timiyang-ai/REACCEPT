public List<String> pendingDrops() {

    List<String> versions = new ArrayList<String>();
    for (Entry value : map.values()) {
      versions.add(value.version.asString());
    }
    return versions;
  }