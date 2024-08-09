@Test
	public void testCreateStudent() {
		// SUCCESS
		StudentData s = new StudentData();
		s.name = "herp derp";
		s.course = "Winzor101";
		s.email = "ching@chang.com";
		
		try {
			accountsDb.createStudent(s);
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
		
		// FAIL : duplicate
		try {
			accountsDb.createStudent(s);
			fail();
		} catch (EntityAlreadyExistsException e) {
			
		}
		
		// FAIL : invalid params
		s.course = "pwned 101";
		try {
			accountsDb.createStudent(s);
			fail();
		} catch (AssertionError a) {
			
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
	}