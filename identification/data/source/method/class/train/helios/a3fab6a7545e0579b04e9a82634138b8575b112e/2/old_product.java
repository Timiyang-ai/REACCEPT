public ListenableFuture<List<String>> listHosts(final List<String> labels) {
    final Multimap<String, String> query = HashMultimap.create();
    query.putAll("labels", labels);

    return listHosts(query);
  }