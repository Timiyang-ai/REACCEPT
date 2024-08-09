@Override
	public boolean covers(Geometry g) {
		// short-circuit test
		if (! getEnvelopeInternal().covers(g.getEnvelopeInternal()))
			return false;
		// optimization for rectangle arguments
		if (isRectangle()) {
			// since we have already tested that the test envelope is covered
			return true;
		}
		double x1,x2,y1,y2;
		x1=g.getEnvelopeInternal().getMinX();
		x2=g.getEnvelopeInternal().getMaxX();
		y1=g.getEnvelopeInternal().getMinY();
		y2=g.getEnvelopeInternal().getMaxY();	
		return covers(x1,y1)&&covers(x1,y2)&&covers(x2,y2)&&covers(x2,y1);
	}