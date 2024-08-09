public Bln isRing(final ANode node) throws QueryException {
    final Geometry geo = geo(node, Q_GML_LINEARRING, Q_GML_LINESTRING);
    if(geo == null && checkGeo(node) != null)
      throw GeoErrors.geoType(node.qname().local(), "Line");

    return Bln.get(geo instanceof LineString ?
       ((LineString) geo).isRing() :
       ((LinearRing) geo).isRing());
  }