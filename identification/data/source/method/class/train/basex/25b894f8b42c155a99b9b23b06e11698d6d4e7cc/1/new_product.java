@Deterministic @Requires(Permission.NONE)
  public ANode buffer(final ANode node, final Dbl distance) throws QueryException {
    return gmlWriter(checkGeo(node).buffer(distance.dbl()));
  }