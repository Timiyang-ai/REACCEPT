public ClassName peerClass(String name) {
    return new ClassName(new ImmutableList.Builder<String>()
        .addAll(names.subList(0, names.size() - 1))
        .add(name)
        .build());
  }