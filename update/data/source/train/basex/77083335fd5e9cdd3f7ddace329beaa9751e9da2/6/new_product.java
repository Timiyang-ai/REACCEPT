private Iter list(final QueryContext ctx) {
    final ItemIter ii = new ItemIter();
    for(final String s : List.list(ctx.context)) ii.add(Str.get(s));
    return ii;
  }