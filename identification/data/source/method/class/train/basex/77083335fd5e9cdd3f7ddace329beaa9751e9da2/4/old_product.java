private Iter list(final QueryContext ctx) {
    final ItemIter ii = new ItemIter();
    for(final String s : List.list(ctx.resource.context)) ii.add(Str.get(s));
    return ii;
  }