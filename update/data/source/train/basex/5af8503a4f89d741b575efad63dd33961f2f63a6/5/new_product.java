public Value startPoint(final ANode node) throws QueryException {
    final Geometry geo = geo(node, Q_GML_LINEARRING, Q_GML_LINESTRING);
    if(geo == null && checkGeo(node) != null)
      throw GeoErrors.geoType(node.qname().local(), "Line");

    return gmlWriter(geo instanceof LineString ?
       ((LineString) geo).getStartPoint() :
       ((LinearRing) geo).getStartPoint());
  }