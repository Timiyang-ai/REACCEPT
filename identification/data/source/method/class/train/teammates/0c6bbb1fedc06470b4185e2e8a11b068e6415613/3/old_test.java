@Test
	public void testCreateSubmission() {
		// SUCCESS
		SubmissionData s = new SubmissionData();
		s.course = "Winzor101";
		s.evaluation = "Evaluation for Winzor101";
		s.team = "Team Derp";
		s.reviewee = "herp.derp@gmail.com";
		s.reviewer = "derp.herp@gmail.com";
		
		try {
			submissionsDb.createSubmission(s);
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
		
		// FAIL : duplicate
		try {
			submissionsDb.createSubmission(s);
			fail();
		} catch (EntityAlreadyExistsException e) {
			
		}
		
		// FAIL : invalid params
		s.reviewer = "herp mc derp";
		try {
			submissionsDb.createSubmission(s);
			fail();
		} catch (AssertionError a) {
			
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
	}