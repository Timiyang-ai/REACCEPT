public void setRadius(Double givenRadius) {
		// Get the internal radius of the object. We need to make sure that the circle at least should be the minimum circumscribed circle
		Envelope centerGeometryMBR = this.centerGeometry.getEnvelopeInternal();
		double width = centerGeometryMBR.getMaxX()-centerGeometryMBR.getMinX();
		double length = centerGeometryMBR.getMaxY()-centerGeometryMBR.getMinY();
		double centerGeometryInternalRadius = Math.sqrt(width*width+length*length)/2;
		this.radius=givenRadius>centerGeometryInternalRadius?givenRadius:centerGeometryInternalRadius;
		this.MBR=new Envelope(this.centerPoint.x-this.radius, this.centerPoint.x+this.radius, this.centerPoint.y-this.radius, this.centerPoint.y+this.radius);
	}