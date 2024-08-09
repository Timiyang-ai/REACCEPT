public ClassName peerClass(String name) {
    List<String> result = new ArrayList<>(names);
    result.set(result.size() - 1, name);
    return new ClassName(result);
  }