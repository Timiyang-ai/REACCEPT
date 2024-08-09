@ContextDependent
  @Requires(Permission.NONE)
  public void updateAttribute(final Str key, final Item item) throws QueryException {
    Item it = item;
    final Data d = it.data();
    if(d != null && !d.inMemory()) {
      // convert database node to main memory data instance
      it = ((ANode) it).dbCopy(context.context.prop);
    } else if(it instanceof FItem) {
      BXSE_FITEM.thrw(null, it);
    }
    session().setAttribute(key.toJava(), it);
  }