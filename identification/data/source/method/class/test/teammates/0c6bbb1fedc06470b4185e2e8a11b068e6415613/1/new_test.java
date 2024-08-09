@Test
	public void testCreateStudent() throws EntityAlreadyExistsException {
		// SUCCESS
		StudentData s = new StudentData();
		s.name = "valid student";
		s.course = "valid-course";
		s.email = "valid@email.com";
		accountsDb.createStudent(s);
			
		// FAIL : duplicate
		try {
			accountsDb.createStudent(s);
			fail();
		} catch (EntityAlreadyExistsException e) {
			assertContains(AccountsDb.ERROR_CREATE_STUDENT_ALREADY_EXISTS, e.getMessage());
		}
		
		// FAIL : invalid params
		s.course = "invalid id space";
		try {
			accountsDb.createStudent(s);
			fail();
		} catch (AssertionError a) {
			assertEquals(a.getMessage(), StudentData.ERROR_FIELD_COURSE);
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
		
		// Null params check:
		try {
			accountsDb.createStudent(null);
			fail();
		} catch (AssertionError a) {
			assertEquals(Common.ERROR_DBLEVEL_NULL_INPUT, a.getMessage());
		}
	}