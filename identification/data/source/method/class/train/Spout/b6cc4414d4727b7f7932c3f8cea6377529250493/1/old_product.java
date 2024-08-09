public static boolean checkCollision(BoundingBox a, BoundingBox b) {
		return a.min.compareTo(b.max) <= 0 && a.max.compareTo(b.min) >= 0;
	}