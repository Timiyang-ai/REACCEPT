@Test
	public void testCreateCoord() throws EntityAlreadyExistsException {
		// SUCCESS
		CoordData c = new CoordData();
		c.id = "valid.id";
		c.name = "John Doe";
		c.email = "john.doe@coordinator.com";
		accountsDb.createCoord(c);
		
		// FAIL : duplicate
		try {
			accountsDb.createCoord(c);
			fail();
		} catch (EntityAlreadyExistsException e) {
			assertContains(AccountsDb.ERROR_CREATE_COORD_ALREADY_EXISTS, e.getMessage());
		}
		
		// FAIL : invalid params
		c.id = "invalid id with spaces";
		try {
			accountsDb.createCoord(c);
			fail();
		} catch (AssertionError a) {
			assertEquals(a.getMessage(), CoordData.ERROR_FIELD_ID);
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
		
		// Null params check:
		try {
			accountsDb.createCoord(null);
			fail();
		} catch (AssertionError a) {
			assertEquals(Common.ERROR_DBLEVEL_NULL_INPUT, a.getMessage());
		}
	}