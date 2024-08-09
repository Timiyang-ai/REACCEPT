public BoundingBox scale(Vector3 scale) {
		min = min.multiply(scale);
		max = max.multiply(scale);
		return this;
	}