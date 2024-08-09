public void createCourse(String coordId, String courseId, String courseName)
			throws EntityAlreadyExistsException, InvalidParametersException {
		Assumption.assertNotNull(ERROR_NULL_PARAMETER, coordId);
		Assumption.assertNotNull(ERROR_NULL_PARAMETER, courseId);
		Assumption.assertNotNull(ERROR_NULL_PARAMETER, courseName);

		verifyCoordUsingOwnIdOrAbove(coordId);

		CourseData courseToAdd = new CourseData(courseId, courseName, coordId);
		
		if (!courseToAdd.isValid()) {
			throw new InvalidParametersException(courseToAdd.getInvalidStateInfo());
		}
		
		CoursesStorage.inst().getDb().createCourse(courseToAdd);
	}