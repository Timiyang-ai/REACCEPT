@Requires(Permission.NONE)
  public void set(final Str key, final Item item) throws QueryException {
    Item it = item;
    final Data d = it.data();
    if(d != null && !d.inMemory()) {
      // convert database node to main memory data instance
      it = ((ANode) it).dbCopy(context.context.prop);
    } else if(it instanceof FItem) {
      throw SessionErrors.functionItem();
    }
    session().setAttribute(key.toJava(), it);
  }