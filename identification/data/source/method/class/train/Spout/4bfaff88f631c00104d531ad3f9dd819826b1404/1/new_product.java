public BoundingBox scale(Vector3 scale) {
		return new BoundingBox(min.multiply(scale), max.multiply(scale));
	}