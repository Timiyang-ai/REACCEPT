@Test
	public void testProcessImportedGrades() {
		final List<Assignment> assignments = mockAssignments();
		final List<GbStudentGradeInfo> grades = mockStudentGrades();
		final ImportedGradeWrapper importedGradeWrapper = mockImportedGrades();

		final List<ProcessedGradeItem> processedGradeItems = ImportGradesHelper.processImportedGrades(importedGradeWrapper, assignments,
				grades);

		Assert.assertNotNull(processedGradeItems);

		Assert.assertEquals("Wrong number of columns", 4, processedGradeItems.size());

		// assignment 1
		Assert.assertEquals("wrong status", ProcessedGradeItemStatus.STATUS_NA, processedGradeItems.get(0).getStatus().getStatusCode());
		Assert.assertEquals("wrong status", ProcessedGradeItemStatus.STATUS_NA,
				processedGradeItems.get(0).getCommentStatus().getStatusCode());

		// assignment 2
		Assert.assertEquals("wrong status", ProcessedGradeItemStatus.STATUS_MODIFIED,
				processedGradeItems.get(1).getStatus().getStatusCode());
		Assert.assertEquals("wrong status", ProcessedGradeItemStatus.STATUS_UPDATE,
				processedGradeItems.get(1).getCommentStatus().getStatusCode());

		// assignment 3
		Assert.assertEquals("wrong status", ProcessedGradeItemStatus.STATUS_NEW, processedGradeItems.get(2).getStatus().getStatusCode());
		Assert.assertEquals("wrong status", ProcessedGradeItemStatus.STATUS_NEW,
				processedGradeItems.get(2).getCommentStatus().getStatusCode());

		// assignment ext
		final ProcessedGradeItemStatus extStatus = processedGradeItems.get(3).getStatus();
		Assert.assertEquals("wrong status", ProcessedGradeItemStatus.STATUS_EXTERNAL, extStatus.getStatusCode());
		Assert.assertEquals("wrong status name", "From a test", extStatus.getStatusValue());

	}