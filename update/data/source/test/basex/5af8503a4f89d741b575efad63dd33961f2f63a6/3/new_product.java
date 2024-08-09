public Dbl z(final ANode node) throws QueryException {
    final Geometry geo = geo(node, Q_GML_POINT);
    if(geo == null && checkGeo(node) != null)
      throw GeoErrors.geoType(node.qname().local(), "Line");

    return Dbl.get(geo.getCoordinate().z);
  }