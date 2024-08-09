@Deterministic @Requires(Permission.NONE)
  public Bln relate(final ANode node1, final ANode node2, final Str intersectionMatrix)
      throws QueryException {
    final Geometry geo1 = checkGeo(node1);
    final Geometry geo2 = checkGeo(node2);
    return Bln.get(geo1.relate(geo2, intersectionMatrix.toJava()));
  }