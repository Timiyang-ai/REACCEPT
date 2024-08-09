private Iter node(final QueryContext ctx, final boolean id)
      throws QueryException {

    return new Iter() {
      final Iter ir = expr[0].iter(ctx);

      @Override
      public Item next() throws QueryException {
        final Item it = ir.next();
        if(it == null) return null;
        final Nod node = checkNode(it);
        if(!(node instanceof DBNode)) NODBCTX.thrw(input, FNDb.this);
        final DBNode dbnode = (DBNode) node;
        return Itr.get(id ? dbnode.data.id(dbnode.pre) : dbnode.pre);
      }
    };
  }