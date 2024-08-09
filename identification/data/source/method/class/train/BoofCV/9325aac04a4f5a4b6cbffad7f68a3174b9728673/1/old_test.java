@Test
	public void equiToNorm() {
		EquirectangularTools_F64 tools = new EquirectangularTools_F64();

		tools.configure(300,250,0,0);

		Vector3D_F64 found = new Vector3D_F64();
		tools.equiToNorm(300.0/2.0, 250.0/2.0, found);

		assertEquals(1.0,found.x, GrlConstants.DOUBLE_TEST_TOL);
		assertEquals(0.0,found.y, GrlConstants.DOUBLE_TEST_TOL);
		assertEquals(0.0,found.z, GrlConstants.DOUBLE_TEST_TOL);

	}