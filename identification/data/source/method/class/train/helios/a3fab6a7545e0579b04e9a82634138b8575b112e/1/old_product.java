public ListenableFuture<List<String>> listHosts(final String namePattern,
                                                  final List<String> labels) {

    final Multimap<String, String> query = HashMultimap.create();
    query.put("namePattern", namePattern);
    query.putAll("labels", labels);

    return listHosts(query);
  }