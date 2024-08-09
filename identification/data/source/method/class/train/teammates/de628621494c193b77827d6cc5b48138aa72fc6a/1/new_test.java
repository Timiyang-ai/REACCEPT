@Test
	public void testAddEvaluation() {
		cout("TestCoordEvaluation: Adding evaluation.");

		gotoEvaluations();
		addEvaluation(sc.evaluation);
		
		gotoEvaluations();
		verifyEvaluationAdded(sc.course.courseId, sc.evaluation.name, "AWAITING", "0 / " + sc.students.size());
	}