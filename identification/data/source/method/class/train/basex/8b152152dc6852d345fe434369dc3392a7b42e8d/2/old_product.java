@ContextDependent
  @Requires(Permission.NONE)
  public Item attribute(final Str key, final Item def) throws QueryException {
    final Object o = session().getAttribute(key.toJava());
    if(o == null) return def;
    if(!(o instanceof Item)) BXSE_GET.thrw(null, Util.name(o));
    return (Item) o;
  }