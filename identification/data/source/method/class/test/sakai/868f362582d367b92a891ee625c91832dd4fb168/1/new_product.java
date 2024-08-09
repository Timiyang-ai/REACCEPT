public static List<ProcessedGradeItem> processImportedGrades(final ImportedSpreadsheetWrapper spreadsheetWrapper,
			final List<Assignment> assignments, final List<GbStudentGradeInfo> currentGrades) {

		// setup
		// note that previous step checks for duplicates
		final List<ProcessedGradeItem> processedGradeItems = new ArrayList<>();

		// process grades
		final Map<Long, AssignmentStudentGradeInfo> transformedGradeMap = transformCurrentGrades(currentGrades);

		// Map assignment name to assignment
		final Map<String, Assignment> assignmentMap = assignments.stream().collect(Collectors.toMap(Assignment::getName, Function.identity()));

		// maintain a list of comment columns so we can check they have a corresponding item
		final List<String> commentColumns = new ArrayList<>();

		//for every column, setup the header and data
		for (final ImportedColumn column : spreadsheetWrapper.getColumns()) {

			// skip the ignorable columns (ie username, id, any marked for ignore)
			if(column.isIgnorable()) {
				continue;
			}

			final String columnTitle = StringUtils.trim(column.getColumnTitle()); // trim whitespace so we can match properly

			final ProcessedGradeItem processedGradeItem = new ProcessedGradeItem();

			//default to gb_item - overridden if a comment type
			processedGradeItem.setType(ProcessedGradeItem.Type.GB_ITEM);

			// assignment info (if available)
			final Assignment assignment = assignmentMap.get(columnTitle);
			if (assignment != null) {
				processedGradeItem.setItemId(assignment.getId());
			}

			// status
			final Status status = determineStatus(column, assignment, spreadsheetWrapper, transformedGradeMap);
			processedGradeItem.setStatus(status);
			processedGradeItem.setItemTitle(columnTitle);

			log.debug("Column name: " + columnTitle + ", type: " + column.getType() + ", status: " + status);

			// process the header as applicable
			if (column.getType() == ImportedColumn.Type.GB_ITEM_WITH_POINTS) {
				processedGradeItem.setItemPointValue(column.getPoints());
			} else if (column.getType() == ImportedColumn.Type.COMMENTS) {
				processedGradeItem.setType(ProcessedGradeItem.Type.COMMENT);
				commentColumns.add(columnTitle);
			} else if (column.getType() == ImportedColumn.Type.GB_ITEM_WITHOUT_POINTS) {
				// nothing todo except avoid the next block
			} else {
				// skip
				//TODO could return this but as a skip status?
				log.warn("Bad column. Type: " + column.getType() + ", header: " + columnTitle + ".  Skipping.");
				continue;
			}

			// process the data
			final List<ProcessedGradeItemDetail> processedGradeItemDetails = new ArrayList<>();
			for (final ImportedRow row : spreadsheetWrapper.getRows()) {
				final ImportedCell cell = row.getCellMap().get(columnTitle);
				if (cell != null) {
					final ProcessedGradeItemDetail processedGradeItemDetail = new ProcessedGradeItemDetail();
					processedGradeItemDetail.setStudentEid(row.getStudentEid());
					processedGradeItemDetail.setStudentUuid(row.getStudentUuid());
					processedGradeItemDetail.setGrade(cell.getScore());
					processedGradeItemDetail.setComment(cell.getComment());
					processedGradeItemDetails.add(processedGradeItemDetail);
				}

			}
			processedGradeItem.setProcessedGradeItemDetails(processedGradeItemDetails);

			processedGradeItems.add(processedGradeItem);
		}

		// comment columns must have an associated gb item column
		// this ensures we have a processed grade item for each one
		commentColumns.forEach(c -> {
			final boolean matchingItemExists = processedGradeItems.stream().filter(p -> StringUtils.equals(c, p.getItemTitle())).findFirst().isPresent();

			if(!matchingItemExists) {
				throw new GbImportCommentMissingItemException("The comment column '" + c + "' does not have a corresponding gradebook item.");
			}
		});

		return processedGradeItems;
	}