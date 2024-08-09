@Ignore
	@Test
	public void testProcessImportedGrades() {
		final List<Assignment> assignments = mockAssignments();
		final List<GbStudentGradeInfo> existingGrades = mockExistingStudentGrades();
		final ImportedSpreadsheetWrapper importedSpreadsheetWrapper = mockImportedSpreadsheetData();

		final List<ProcessedGradeItem> processedGradeItems = ImportGradesHelper.processImportedGrades(importedSpreadsheetWrapper, assignments, existingGrades);

		Assert.assertNotNull(processedGradeItems);

		Assert.assertEquals("Wrong number of columns", 4, processedGradeItems.size());

		// assignment 1
		final ProcessedGradeItem item1 = processedGradeItems.get(0);
		Assert.assertEquals("Incorrect title: " + "Assignment 1", item1.getItemTitle());
		Assert.assertEquals("wrong status", Status.SKIP, item1.getStatus());

		// assignment 2
		final ProcessedGradeItem item2 = processedGradeItems.get(1);
		Assert.assertEquals("Incorrect title: " + "Assignment 2", item2.getItemTitle());
		Assert.assertEquals("wrong status", Status.MODIFIED, item2.getStatus());

		// assignment 3
		// this does not exist in the mocked data so should be new
		final ProcessedGradeItem item3 = processedGradeItems.get(2);
		Assert.assertEquals("Incorrect title: " + "Assignment 3", item3.getItemTitle());
		Assert.assertEquals("wrong status", Status.NEW, item3.getStatus());

		// assignment ext
		final ProcessedGradeItem item4 = processedGradeItems.get(3);
		Assert.assertEquals("Incorrect title: " + "Assignment Ext", item4.getItemTitle());
		Assert.assertEquals("wrong status", Status.EXTERNAL, item4.getStatus());

	}