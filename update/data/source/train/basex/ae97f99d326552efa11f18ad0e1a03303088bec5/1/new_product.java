public Bln isClosed(final ANode node) throws QueryException {
			
      // Check QName
      QNm[] line = {Q_GML_LINEARRING, Q_GML_LINESTRING, Q_GML_MULTILINESTRING};
      QNm[] other = {Q_GML_POLYGON, Q_GML_MULTIPOINT, Q_GML_MULTIPOLYGON,
                      Q_GML_POINT};
      if(checkNode(node, line)) {
        Geometry geom = gmlReader(node);
				if (geom instanceof LineString) 
				 return Bln.get(((LineString) geom).isClosed());
					  
				if (geom instanceof LinearRing) 
				 return Bln.get(((LinearRing) geom).isClosed());
				  
				if (geom instanceof MultiLineString) 
				 return Bln.get(((MultiLineString) geom).isClosed());
			}
			if (checkNode(node, other))
			 throw GeoErrors.lineNeeded(node.qname().local());
			throw GeoErrors.unrecognizedGeo(node.qname().local());
		}