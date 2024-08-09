public void createStudent(StudentData studentData)
			throws EntityAlreadyExistsException, InvalidParametersException {

		verifyCourseOwnerOrAbove(studentData.course);
		
		if (!studentData.isValid()) {
			throw new InvalidParametersException(studentData.getInvalidStateInfo());
		}

		AccountsStorage.inst().getDb().createStudent(studentData);

		// adjust existing evaluations to accommodate new student
		EvaluationsStorage.inst().adjustSubmissionsForNewStudent(
				studentData.course, studentData.email,
				studentData.team);
	}