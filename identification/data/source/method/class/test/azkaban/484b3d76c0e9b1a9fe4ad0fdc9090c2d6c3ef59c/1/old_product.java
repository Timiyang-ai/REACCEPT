public NodeBuilder createNode(final String name, final NodeProcessor nodeProcessor) {
    final NodeBuilder builder = new NodeBuilder(name, nodeProcessor, this);
    this.builders.add(builder);
    if (this.nodeNamesSet.contains(name)) {
      throw new DagException(String.format("Node names in %s need to be unique. The name "
          + "(%s) already exists.", this, name));
    }
    this.nodeNamesSet.add(name);

    return builder;
  }