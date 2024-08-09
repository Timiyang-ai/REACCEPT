@Test
	public void triangulate_N() {
		createScene();

		TriangulateLinearDLT alg = new TriangulateLinearDLT();

		Point3D_F64 found = new Point3D_F64();

		alg.triangulate(obsPts, motionWorldToCamera,found);

		assertEquals(worldPoint.x,found.x,1e-8);
		assertEquals(worldPoint.y,found.y,1e-8);
		assertEquals(worldPoint.z,found.z,1e-8);
	}