public static List<ProcessedGradeItem> processImportedGrades(final ImportedGradeWrapper importedGradeWrapper,
			final List<Assignment> assignments, final List<GbStudentGradeInfo> currentGrades) {

		//setup
		final List<ProcessedGradeItem> processedGradeItems = new ArrayList<>();
		final Map<String, Assignment> assignmentNameMap = new HashMap<>();
		final Map<String, ProcessedGradeItem> assignmentProcessedGradeItemMap = new HashMap<>();

		// process grades
		final Map<Long, AssignmentStudentGradeInfo> transformedGradeMap = transformCurrentGrades(currentGrades);


		// Map the assignment name back to the Id
		for (final Assignment assignment : assignments) {
			assignmentNameMap.put(assignment.getName(), assignment);
		}

		//for every column, setup the data
		for (final ImportColumn column : importedGradeWrapper.getColumns()) {
			boolean needsAdded = false;

			final String columnTitle = StringUtils.trim(column.getColumnTitle()); // trim whitespace so we can match properly

			//setup a new one unless it already exists (ie there were duplicate columns)
			ProcessedGradeItem processedGradeItem = assignmentProcessedGradeItemMap.get(columnTitle);
			if (processedGradeItem == null) {
				processedGradeItem = new ProcessedGradeItem();
				needsAdded = true;

				//default to gb_item
				//overridden if a comment type
				processedGradeItem.setType(ProcessedGradeItem.Type.GB_ITEM);
			}

			final Assignment assignment = assignmentNameMap.get(columnTitle);
			final ProcessedGradeItemStatus status = determineStatus(column, assignment, importedGradeWrapper, transformedGradeMap);

			// skip the student columns
			if(StringUtils.equals(column.getColumnTitle(), IMPORT_USER_ID) ||
					StringUtils.equals(column.getColumnTitle(), IMPORT_USER_NAME)) {
				continue;
			}

			if (column.getType() == ImportColumn.Type.GB_ITEM_WITH_POINTS) {
				log.debug("GB Item: " + columnTitle + ", status: " + status.getStatusCode());
				processedGradeItem.setItemTitle(columnTitle);
				processedGradeItem.setItemPointValue(column.getPoints());
				processedGradeItem.setStatus(status);
			} else if (column.getType() == ImportColumn.Type.COMMENTS) {
				log.debug("Comments: " + columnTitle + ", status: " + status.getStatusCode());
				processedGradeItem.setType(ProcessedGradeItem.Type.COMMENT);
				processedGradeItem.setCommentStatus(status);
			} else if (column.getType() == ImportColumn.Type.GB_ITEM_WITHOUT_POINTS) {
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
			for (final ImportedGrade importedGrade : importedGradeWrapper.getImportedGrades()) {
				final ImportedGradeItem importedGradeItem = importedGrade.getGradeItemMap().get(columnTitle);
				if (importedGradeItem != null) {
					final ProcessedGradeItemDetail processedGradeItemDetail = new ProcessedGradeItemDetail();
					processedGradeItemDetail.setStudentEid(importedGrade.getStudentEid());
					processedGradeItemDetail.setStudentUuid(importedGrade.getStudentUuid());
					processedGradeItemDetail.setGrade(importedGradeItem.getGradeItemScore());
					processedGradeItemDetail.setComment(importedGradeItem.getGradeItemComment());
					processedGradeItemDetails.add(processedGradeItemDetail);
				}

			}
			processedGradeItem.setProcessedGradeItemDetails(processedGradeItemDetails);

			if (needsAdded) {
				processedGradeItems.add(processedGradeItem);
				assignmentProcessedGradeItemMap.put(columnTitle, processedGradeItem);
			}
		}

		// comment columns must have an associated gb item column
		// this ensures we have a processed grade item for each one
		final List<ProcessedGradeItem> commentColumns = processedGradeItems.stream().filter(p -> p.getType() == ProcessedGradeItem.Type.COMMENT).collect(Collectors.toList());

		commentColumns.forEach(c -> {
			final boolean matchingItemExists = processedGradeItems.stream().filter(p -> StringUtils.equals(c.getItemTitle(), p.getItemTitle())).findFirst().isPresent();
			if(!matchingItemExists) {
				throw new GbImportCommentMissingItemException("The comment column '" + c + "' does not have a corresponding gradebook item.");
			}
		});

		return processedGradeItems;

	}