@Test
	public void testCreateSubmission() throws EntityAlreadyExistsException {
		// SUCCESS
		SubmissionData s = new SubmissionData();
		s.course = "Computing101";
		s.evaluation = "Basic Computing Evaluation1";
		s.team = "team1";
		s.reviewee = "student1@gmail.com";
		s.reviewer = "student2@gmail.com";
		submissionsDb.createSubmission(s);
			
		// FAIL : duplicate
		try {
			submissionsDb.createSubmission(s);
			fail();
		} catch (EntityAlreadyExistsException e) {
			assertContains(SubmissionsDb.ERROR_CREATE_SUBMISSION_ALREADY_EXISTS, e.getMessage());
		}
		
		// FAIL : invalid params
		s.reviewer = "invalid.email";
		try {
			submissionsDb.createSubmission(s);
			fail();
		} catch (AssertionError a) {
			assertEquals(a.getMessage(), SubmissionData.ERROR_FIELD_REVIEWER);
		} catch (EntityAlreadyExistsException e) {
			fail();
		}
		
		// Null params check:
		try {
			submissionsDb.createSubmission(null);
			fail();
		} catch (AssertionError a) {
			assertEquals(Common.ERROR_DBLEVEL_NULL_INPUT, a.getMessage());
		}
	}