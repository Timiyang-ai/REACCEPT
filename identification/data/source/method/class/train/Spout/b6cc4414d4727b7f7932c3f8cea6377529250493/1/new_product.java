public static boolean checkCollision(BoundingBox a, BoundingBox b) {
		return a.min.getX() < b.max.getX() &&
				a.min.getY() < b.max.getY() &&
				a.min.getZ() < b.max.getZ() &&
				a.max.getX() > b.min.getX() &&
				a.max.getY() > b.min.getY() &&
				a.max.getZ() > b.min.getZ();
	}