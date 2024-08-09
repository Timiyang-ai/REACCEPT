  private Node createNode(final String name) {
    return this.dagBuilder.createNode(name, mock(NodeProcessor.class));
  }