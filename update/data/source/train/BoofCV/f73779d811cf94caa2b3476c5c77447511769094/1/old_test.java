@Test
	public void equiToLonlat_reverse() {

		EquirectangularTools_F64 tools = new EquirectangularTools_F64();
		tools.configure(width,height);

		testCoordinate(tools, width/2, height/2);
		testCoordinate(tools, 0, height/2);
		testCoordinate(tools, width-1, height/2);
		testCoordinate(tools, width/2, 0);
		testCoordinate(tools, width/2, height-1);

	}