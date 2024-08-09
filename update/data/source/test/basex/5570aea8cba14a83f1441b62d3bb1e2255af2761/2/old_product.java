public final void serialize(final Item item) throws IOException {
    openResult();
    if(item instanceof ANode) {
      final Type type = item.type;
      if(type == NodeType.ATT) SERATTR.thrwSerial(item);
      if(type == NodeType.NSP) SERNS.thrwSerial(item);
      serialize((ANode) item);
    } else if(item instanceof FItem) {
      SERFUNC.thrwSerial(item.description());
    } else {
      finishElement();
      atomic(item);
    }
    closeResult();
  }