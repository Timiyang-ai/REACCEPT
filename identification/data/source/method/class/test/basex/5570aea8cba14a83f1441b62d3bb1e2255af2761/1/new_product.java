public final void serialize(final Item item) throws IOException {
    openResult();
    if(item instanceof ANode) {
      final Type type = item.type;
      if(type == NodeType.ATT) SERATTR.thrwIO(item);
      if(type == NodeType.NSP) SERNS.thrwIO(item);
      serialize((ANode) item);
    } else if(item instanceof FItem) {
      SERFUNC.thrwIO(item.description());
    } else {
      finishElement();
      atomic(item);
    }
    closeResult();
  }