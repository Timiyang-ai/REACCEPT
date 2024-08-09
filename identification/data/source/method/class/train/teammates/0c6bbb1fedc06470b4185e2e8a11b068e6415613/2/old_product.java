public void createEvaluation(EvaluationData evaluation)
			throws EntityAlreadyExistsException, InvalidParametersException {

		verifyCourseOwnerOrAbove(evaluation.course);
		
		if (!evaluation.isValid()) {
			throw new InvalidParametersException(evaluation.getInvalidStateInfo());
		}

		EvaluationsStorage.inst().createEvaluation(evaluation);
	}