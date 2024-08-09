@Requires(Permission.NONE)
  public Item get(final Str key, final Item def) throws QueryException {
    final Object o = session().getAttribute(key.toJava());
    if(o == null) return def;
    if(o instanceof Item) return (Item) o;
    throw SessionErrors.noAttribute(Util.className(o));
  }