public Int getSRID(final ANode node) throws QueryException {

    // Check QName
    if(checkNode(node, new QNm[0])) {
      Geometry geom = gmlReader(node);
      return Int.get(geom.getSRID());
    }
    return null;
  }