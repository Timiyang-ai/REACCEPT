private static List<Query> splitQuery(Query query, @Nullable String namespace,
        Datastore datastore, QuerySplitter querySplitter, int numSplits) throws DatastoreException {
      // If namespace is set, include it in the split request so splits are calculated accordingly.
      PartitionId.Builder partitionBuilder = PartitionId.newBuilder();
      if (namespace != null) {
        partitionBuilder.setNamespaceId(namespace);
      }

      return querySplitter.getSplits(query, partitionBuilder.build(), numSplits, datastore);
    }