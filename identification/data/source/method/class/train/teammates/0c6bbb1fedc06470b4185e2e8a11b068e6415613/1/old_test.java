@Test
	public void testCreateCoord() {
		// SUCCESS
		CoordData c = new CoordData();
		c.id = "herp.derp";
		c.name = "Herp McDerpson";
		c.email = "ching@chang.com";
		
		try {
			accountsDb.createCoord(c);
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
		
		// FAIL : duplicate
		try {
			accountsDb.createCoord(c);
			fail();
		} catch (EntityAlreadyExistsException e) {
			
		}
		
		// FAIL : invalid params
		c.id = "herp mc derp";
		try {
			accountsDb.createCoord(c);
			fail();
		} catch (AssertionError a) {
			
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
	}