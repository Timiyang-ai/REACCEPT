@Test
	public void testScale() {
		BoundingBox a = new BoundingBox(-0.3f, -0.5f, -0.7f, 0.12f, 0.43f, 0.1f);
		BoundingBox b = new BoundingBox(-4.5f, -7.5f, -10.5f, 1.8f, 6.45f, 1.5f);
		a.scale(1.5f);
		b.scale(0.1f);


		testValue(a, b);

		a.scale(new Vector3(0.6f, 0.6f, 0.6f));
		b.scale(-0.3f, -0.3f, -0.3f).scale(-2.0f);

		testValue(a, b);
	}