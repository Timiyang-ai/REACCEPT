@Test
	public void testAddEvaluation() {
		cout("Test: Adding evaluation.");

		addEvaluation(sc.evaluation);
		gotoEvaluations();
		verifyEvaluationAdded(sc.course.courseId, sc.evaluation.name,
				"AWAITING", "0 / " + sc.students.size());
	}