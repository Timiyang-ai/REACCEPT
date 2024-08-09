@Test
	public void testCreateEvaluation() throws EntityAlreadyExistsException {
		// SUCCESS
		EvaluationData e = new EvaluationData();
		e.course = "Computing101";
		e.name = "Basic Computing Evaluation1";
		e.startTime = new Date();
		e.endTime = new Date();
		evaluationsDb.createEvaluation(e);
		
		// FAIL : duplicate
		try {
			evaluationsDb.createEvaluation(e);
			fail();
		} catch (EntityAlreadyExistsException ex) {
			assertContains(EvaluationsDb.ERROR_CREATE_EVALUATION_ALREADY_EXISTS, ex.getMessage());
		}
		
		// FAIL : invalid params
		e.startTime = null;
		try {
			evaluationsDb.createEvaluation(e);
			fail();
		} catch (AssertionError a) {
			assertEquals(a.getMessage(), EvaluationData.ERROR_FIELD_STARTTIME);
		} catch (EntityAlreadyExistsException ex) {
			fail();
		}
		
		// Null params check:
		try {
			evaluationsDb.createEvaluation(null);
			fail();
		} catch (AssertionError a) {
			assertEquals(Common.ERROR_DBLEVEL_NULL_INPUT, a.getMessage());
		}
	}