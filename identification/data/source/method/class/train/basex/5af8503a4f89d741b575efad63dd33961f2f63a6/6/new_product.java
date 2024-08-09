public Int srid(final ANode node) throws QueryException {
    return Int.get(checkGeo(node).getSRID());
  }