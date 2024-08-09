public Value pointN(final ANode node, final Int number) throws QueryException {
    final Geometry geo = geo(node, Q_GML_LINEARRING, Q_GML_LINESTRING);
    if(geo == null && checkGeo(node) != null)
      throw GeoErrors.lineNeeded(node.qname().local());

    final int max = geo.getNumPoints();
    final long n = number.itr();
    if(n < 1 || n > max) throw GeoErrors.outOfRangeIdx(number);

    return gmlWriter(geo instanceof LineString ?
       ((LineString) geo).getPointN((int) n - 1) :
       ((LinearRing) geo).getPointN((int) n - 1));
  }