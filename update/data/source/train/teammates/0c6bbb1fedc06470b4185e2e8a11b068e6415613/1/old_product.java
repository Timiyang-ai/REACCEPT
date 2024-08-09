public void createSubmission(SubmissionData submissionToAdd) throws EntityAlreadyExistsException {

		Assumption.assertTrue(submissionToAdd.getInvalidStateInfo(), submissionToAdd.isValid());
		
		if (getSubmissionEntity(submissionToAdd.course,
				submissionToAdd.evaluation, submissionToAdd.reviewee,
				submissionToAdd.reviewer) != null) {
			String error = "Trying to create a Submission that exists: "
					+ "course: " + submissionToAdd.course + ", evaluation: "
					+ submissionToAdd.evaluation + ", toStudent: "
					+ submissionToAdd.reviewee + ", fromStudent: "
					+ submissionToAdd.reviewer;
			
			log.warning(error + "\n" + Common.getCurrentThreadStack());

			throw new EntityAlreadyExistsException(error);
		}

		Submission newSubmission = submissionToAdd.toEntity();

		try {
			getPM().makePersistent(newSubmission);
			getPM().flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// check if insert operation persisted
		int elapsedTime = 0;
		Submission submissionCheck = getSubmissionEntity(
				submissionToAdd.course, submissionToAdd.evaluation,
				submissionToAdd.reviewee, submissionToAdd.reviewer);
		while ((submissionCheck == null)
				&& (elapsedTime < Common.PERSISTENCE_CHECK_DURATION)) {
			Common.waitBriefly();
			submissionCheck = getSubmissionEntity(submissionToAdd.course,
					submissionToAdd.evaluation, submissionToAdd.reviewee,
					submissionToAdd.reviewer);
			elapsedTime += Common.WAIT_DURATION;
		}
		if (elapsedTime == Common.PERSISTENCE_CHECK_DURATION) {
			log.severe("Operation did not persist in time: createSubmission->"
					+ submissionToAdd.course + "/" + submissionToAdd.evaluation
					+ " | to: " + submissionToAdd.reviewee + " | from: "
					+ submissionToAdd.reviewer);
		}
	}