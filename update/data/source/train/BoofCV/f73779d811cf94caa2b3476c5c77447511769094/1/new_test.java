@Test
	public void equiToLonlat_reverse() {

		EquirectangularTools_F64 tools = new EquirectangularTools_F64();
		tools.configure(width,height);

		equiToLonlat_reverse(tools, width/2, height/2);
		equiToLonlat_reverse(tools, 0, height/2);
		equiToLonlat_reverse(tools, width-1, height/2);
		equiToLonlat_reverse(tools, width/2, 0);
		equiToLonlat_reverse(tools, width/2, height-1);

	}