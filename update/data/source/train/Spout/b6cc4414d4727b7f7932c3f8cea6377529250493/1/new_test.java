@Test
	public void testCheckCollision_BoundingBox_BoundingBox() {
		BoundingBox a = new BoundingBox(new Vector3(1, 1, 1), new Vector3(3, 3, 3));
		BoundingBox b = new BoundingBox(new Vector3(2, 2, 2), new Vector3(4, 4, 4));
		assertTrue(CollisionHelper.checkCollision(a, b));

		//Check shared points
		a = new BoundingBox(new Vector3(4, 4, 4), new Vector3(6, 6, 6));
		assertFalse(CollisionHelper.checkCollision(a, b));

		//Check not intersecting
		a = new BoundingBox(new Vector3(6, 6, 6), new Vector3(10, 10, 10));
		assertFalse(CollisionHelper.checkCollision(a, b));
	}