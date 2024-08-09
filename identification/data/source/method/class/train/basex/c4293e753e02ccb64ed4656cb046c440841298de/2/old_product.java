public Value interiorRingN(final ANode node, Int ringNumber) throws QueryException {
			
			// Check Node
			  QNm[] polygon = {Q_GML_POLYGON};
				QNm[] other = {Q_GML_POINT, Q_GML_LINEARRING, Q_GML_LINESTRING,
	            Q_GML_MULTILINESTRING, Q_GML_MULTIPOINT, Q_GML_MULTIPOLYGON};
				if (checkNode(node, polygon)) {
				  Geometry geom = gmlReader(node);
				  
				  int temp = ((Polygon) geom).getNumInteriorRing();
				  if (((BigInteger) ringNumber.toJava()).intValue() < 1 
				      || ((BigInteger) ringNumber.toJava()).intValue() > temp)
				    throw GeoErrors.outOfRangeIdx(ringNumber); 	  
			    
				  LineString ring = ((Polygon) geom).getInteriorRingN(((BigInteger) ringNumber.toJava()).intValue() - 1);
			    // Write the Geometry in GML2 format
			    return gmlWriter(ring);
				}
        if (checkNode(node, other))
          throw GeoErrors.polygonNeeded(node.qname().local());
        throw GeoErrors.unrecognizedGeo(node.qname().local());       
		}