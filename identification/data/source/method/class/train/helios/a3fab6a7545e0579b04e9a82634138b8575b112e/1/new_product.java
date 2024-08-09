public ListenableFuture<List<String>> listHosts(final String namePattern,
                                                  final Set<String> unparsedHostSelectors) {

    final Multimap<String, String> query = HashMultimap.create();
    query.put("namePattern", namePattern);
    query.putAll("selector", unparsedHostSelectors);

    return listHosts(query);
  }