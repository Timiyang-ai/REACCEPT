public boolean covers(double x, double y) {
		double distance = Math.sqrt((x-this.centerPoint.x)*(x-this.centerPoint.x)+(y-this.centerPoint.y)*(y-this.centerPoint.y));
		return distance<=this.radius?true:false;
	}