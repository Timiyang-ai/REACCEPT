public Node createNode(final String name, final NodeProcessor nodeProcessor) {
    checkIsBuilt();

    if (this.nameToNodeMap.get(name) != null) {
      throw new DagException(String.format("Node names in %s need to be unique. The name "
          + "(%s) already exists.", this, name));
    }
    final Node node = new Node(name, nodeProcessor, this.dag);
    this.nameToNodeMap.put(name, node);

    return node;
  }