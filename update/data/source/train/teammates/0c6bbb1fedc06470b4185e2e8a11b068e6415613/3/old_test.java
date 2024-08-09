@Test
	public void testCreateCourse() {
		// SUCCESS
		CourseData c = new CourseData();
		c.id = "Winzor101";
		c.name = "Basic Herping Derping";
		c.coord = "herp.derp";
		
		try {
			coursesDb.createCourse(c);
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
		
		// FAIL : duplicate
		try {
			coursesDb.createCourse(c);
			fail();
		} catch (EntityAlreadyExistsException e) {
			
		}
		
		// FAIL : invalid params
		c.id = "herp mc derp";
		try {
			coursesDb.createCourse(c);
			fail();
		} catch (AssertionError a) {
			
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
	}