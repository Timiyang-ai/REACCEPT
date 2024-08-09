public NodeBuilder createNode(final String name, final NodeProcessor nodeProcessor) {
    if (this.nodeNamesSet.contains(name)) {
      throw new DagException(String.format("Node names in %s need to be unique. The name "
          + "(%s) already exists.", this, name));
    }
    final NodeBuilder builder = new NodeBuilder(name, nodeProcessor, this);
    this.builders.add(builder);
    this.nodeNamesSet.add(name);

    return builder;
  }