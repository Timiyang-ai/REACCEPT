public Bln isClosed(final ANode node) throws QueryException {
    final Geometry geo = geo(node,
        Q_GML_LINEARRING, Q_GML_LINESTRING, Q_GML_MULTILINESTRING);
    if(geo == null && checkGeo(node) != null)
      throw GeoErrors.geoType(node.qname().local(), "Line");

    return Bln.get(geo instanceof LineString ?
       ((LineString) geo).isClosed() : geo instanceof LinearRing ?
       ((LinearRing) geo).isClosed() :
       ((MultiLineString) geo).isClosed());
  }