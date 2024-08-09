public Value interiorRingN(final ANode node, final Int number) throws QueryException {
    final Geometry geo = geo(node, Q_GML_POLYGON);
    if(geo == null && checkGeo(node) != null)
      throw GeoErrors.polygonNeeded(node.qname().local());

    final long n = number.itr();
    final int max = ((Polygon) geo).getNumInteriorRing();
    if(n < 1 || n > max) throw GeoErrors.outOfRangeIdx(number);
    return gmlWriter(((Polygon) geo).getInteriorRingN((int) n - 1));
  }