public Value geometryN(final ANode node, final Int geoNumber) throws QueryException {

    // Check QName
    if(checkNode(node, new QNm[0])) {
      Geometry geom = gmlReader(node);
      int temp = geom.getNumGeometries();
      if (((BigInteger) geoNumber.toJava()).intValue() < 1
          || ((BigInteger) geoNumber.toJava()).intValue() > temp)
        throw GeoErrors.outOfRangeIdx(geoNumber);
      Geometry geo = geom.getGeometryN(
          ((BigInteger) geoNumber.toJava()).intValue() - 1);
      //Write the Geometry in GML2 format
      return gmlWriter(geo);
      }
    return null;
    }