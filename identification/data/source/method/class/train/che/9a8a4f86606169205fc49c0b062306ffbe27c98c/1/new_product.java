public List<N> sort(Collection<N> nodes) {
    // the linked hashmap is important to retain the original order of elements unless required
    // by the dependencies between nodes
    LinkedHashMap<ID, NodeInfo<ID, N>> nodeInfos = newLinkedHashMapWithExpectedSize(nodes.size());
    List<NodeInfo<ID, N>> results = new ArrayList<>(nodes.size());

    int pos = 0;
    boolean needsSorting = false;
    for (N node : nodes) {
      ID nodeID = identityExtractor.apply(node);
      // we need the set to be modifiable, so let's make our own
      Set<ID> preds = new HashSet<>(directPredecessorsExtractor.apply(node));
      needsSorting = needsSorting || !preds.isEmpty();

      NodeInfo<ID, N> nodeInfo = nodeInfos.computeIfAbsent(nodeID, __ -> new NodeInfo<>());
      nodeInfo.id = nodeID;
      nodeInfo.predecessors = preds;
      nodeInfo.sourcePosition = pos++;
      nodeInfo.node = node;

      for (ID pred : preds) {
        // note that this means that we're inserting the nodeinfos into the map in an incorrect
        // order and will have to sort them in the source order before we do the actual topo sort.
        // We take that cost because we gamble on there being no dependencies in the nodes as a
        // common case.
        NodeInfo<ID, N> predNode = nodeInfos.computeIfAbsent(pred, __ -> new NodeInfo<>());
        if (predNode.successors == null) {
          predNode.successors = new HashSet<>();
        }
        predNode.successors.add(nodeID);
      }
    }

    if (needsSorting) {
      // because of the predecessors, we have put the nodeinfos in the map in an incorrect order.
      // we need to correct that before we try to sort...
      TreeSet<NodeInfo<ID, N>> tmp = new TreeSet<>(Comparator.comparingInt(a -> a.sourcePosition));
      tmp.addAll(nodeInfos.values());
      nodeInfos.clear();
      tmp.forEach(ni -> nodeInfos.put(ni.id, ni));

      // now we're ready to produce the results
      sort(nodeInfos, results);
    } else {
      // we don't need to sort, but we need to keep the expected behavior of removing the duplicates
      results = new ArrayList<>(nodeInfos.values());
    }

    return results.stream().map(ni -> ni.node).collect(Collectors.toList());
  }