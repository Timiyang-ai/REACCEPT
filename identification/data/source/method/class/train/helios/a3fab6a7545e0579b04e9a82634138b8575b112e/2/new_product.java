public ListenableFuture<List<String>> listHosts(final Set<String> unparsedHostSelectors) {
    final Multimap<String, String> query = HashMultimap.create();
    query.putAll("selector", unparsedHostSelectors);

    return listHosts(query);
  }