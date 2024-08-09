@Test
	public void testCreateCourse() throws EntityAlreadyExistsException {
		// SUCCESS
		CourseData c = new CourseData();
		c.id = "Computing101";
		c.name = "Basic Computing";
		c.coord = "valid.id";
		coursesDb.createCourse(c);
		
		// FAIL : duplicate
		try {
			coursesDb.createCourse(c);
			fail();
		} catch (EntityAlreadyExistsException e) {
			assertContains(CoursesDb.ERROR_CREATE_COURSE_ALREADY_EXISTS, e.getMessage());
		}
		
		// FAIL : invalid params
		c.id = "invalid id spaces";
		try {
			coursesDb.createCourse(c);
			fail();
		} catch (AssertionError a) {
			assertEquals(CourseData.ERROR_ID_INVALIDCHARS, a.getMessage());
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
		
		// Null params check:
		try {
			coursesDb.createCourse(null);
			fail();
		} catch (AssertionError a) {
			assertEquals(Common.ERROR_DBLEVEL_NULL_INPUT, a.getMessage());
		}
	}