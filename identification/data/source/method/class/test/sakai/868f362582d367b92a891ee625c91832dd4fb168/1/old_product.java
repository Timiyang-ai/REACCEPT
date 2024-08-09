public static List<ProcessedGradeItem> processImportedGrades(final ImportedSpreadsheetWrapper spreadsheetWrapper,
			final List<Assignment> assignments, final List<GbStudentGradeInfo> currentGrades) {

		// setup
		// TODO this will ensure dupes can't be added. Provide a report to the user that dupes were added. There would need to be a step before this though
		// this retains order of the columns in the imported file
		final Map<String, ProcessedGradeItem> assignmentProcessedGradeItemMap = new LinkedHashMap<>();

		// process grades
		final Map<Long, AssignmentStudentGradeInfo> transformedGradeMap = transformCurrentGrades(currentGrades);

		// Map assignment name to assignment
		final Map<String, Assignment> assignmentNameMap = assignments.stream().collect(Collectors.toMap(Assignment::getName, a -> a));

		// maintain a list of comment columns so we can check they have a corresponding item
		final List<String> commentColumns = new ArrayList<>();

		//for every column, setup the data
		for (final ImportedColumn column : spreadsheetWrapper.getColumns()) {
			boolean needsToBeAdded = false;

			// skip the ignorable columns
			if(column.isIgnorable()) {
				continue;
			}

			final String columnTitle = StringUtils.trim(column.getColumnTitle()); // trim whitespace so we can match properly

			//setup a new one unless it already exists (ie there were duplicate columns)
			ProcessedGradeItem processedGradeItem = assignmentProcessedGradeItemMap.get(columnTitle);
			if (processedGradeItem == null) {
				processedGradeItem = new ProcessedGradeItem();
				needsToBeAdded = true;

				//default to gb_item
				//overridden if a comment type
				processedGradeItem.setType(ProcessedGradeItem.Type.GB_ITEM);
			}

			final Assignment assignment = assignmentNameMap.get(columnTitle);
			final ProcessedGradeItemStatus status = determineStatus(column, assignment, spreadsheetWrapper, transformedGradeMap);


			if (column.getType() == ImportedColumn.Type.GB_ITEM_WITH_POINTS) {
				log.debug("GB Item: " + columnTitle + ", status: " + status.getStatusCode());
				processedGradeItem.setItemTitle(columnTitle);
				processedGradeItem.setItemPointValue(column.getPoints());
				processedGradeItem.setStatus(status);
			} else if (column.getType() == ImportedColumn.Type.COMMENTS) {
				log.debug("Comments: " + columnTitle + ", status: " + status.getStatusCode());
				processedGradeItem.setType(ProcessedGradeItem.Type.COMMENT);
				processedGradeItem.setCommentStatus(status);
				commentColumns.add(columnTitle);
			} else if (column.getType() == ImportedColumn.Type.GB_ITEM_WITHOUT_POINTS) {
				log.debug("Regular: " + columnTitle + ", status: " + status.getStatusCode());
				processedGradeItem.setItemTitle(columnTitle);
				processedGradeItem.setStatus(status);
			} else {
				// skip
				//TODO could return this but as a skip status?
				log.warn("Bad column. Type: " + column.getType() + ", header: " + columnTitle + ".  Skipping.");
				continue;
			}

			if (assignment != null) {
				processedGradeItem.setItemId(assignment.getId());
			}

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

			// add to list
			if (needsToBeAdded) {
				assignmentProcessedGradeItemMap.put(columnTitle, processedGradeItem);
			}
		}

		// get just a list
		final List<ProcessedGradeItem> processedGradeItems = new ArrayList<>(assignmentProcessedGradeItemMap.values());

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