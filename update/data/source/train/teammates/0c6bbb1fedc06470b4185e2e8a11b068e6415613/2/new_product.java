public void createEvaluation(EvaluationData evaluation)
			throws EntityAlreadyExistsException, InvalidParametersException {
		Assumption.assertNotNull(ERROR_NULL_PARAMETER, evaluation);

		verifyCourseOwnerOrAbove(evaluation.course);
		
		if (!evaluation.isValid()) {
			throw new InvalidParametersException(evaluation.getInvalidStateInfo());
		}

		EvaluationsStorage.inst().createEvaluation(evaluation);
	}