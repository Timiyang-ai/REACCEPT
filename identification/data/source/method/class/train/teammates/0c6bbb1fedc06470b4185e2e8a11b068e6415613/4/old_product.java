public void createCourse(String coordId, String courseId, String courseName)
			throws EntityAlreadyExistsException, InvalidParametersException {

		verifyCoordUsingOwnIdOrAbove(coordId);

		CourseData courseToAdd = new CourseData(courseId, courseName, coordId);
		
		if (!courseToAdd.isValid()) {
			throw new InvalidParametersException(courseToAdd.getInvalidStateInfo());
		}
		
		CoursesStorage.inst().getDb().createCourse(courseToAdd);
	}