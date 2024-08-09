protected boolean isEligible(IDunningContext context, IDunnableDoc sourceDoc)
	{
		final IDunningDAO dunningDAO = Services.get(IDunningDAO.class);

		final int tableId = MTable.getTable_ID(sourceDoc.getTableName());
		final int recordId = sourceDoc.getRecordId();
		final I_C_DunningLevel dunningLevel = context.getC_DunningLevel();

		List<I_C_Dunning_Candidate> previousCandidates = null;

		//
		// Validate DunningGrace
		if (sourceDoc.getGraceDate() != null && sourceDoc.getGraceDate().compareTo(context.getDunningDate()) >= 0)
		{
			logger.info("Skip because DunnableDoc's grace date is >= context DunningDate");
			return false;
		}

		//
		// If sequentially we must check for other levels with smaller days for
		// which this invoice is not yet included!
		if (dunningLevel.getC_Dunning().isCreateLevelsSequentially())
		{
			final List<I_C_DunningLevel> previousLevels = Services.get(IDunningBL.class).getPreviousLevels(dunningLevel);
			if (previousLevels.isEmpty())
			{
				// This is the first Level on which we dunn => consider it eligible
				return true;
			}

			previousCandidates = dunningDAO.retrieveDunningCandidates(context, tableId, recordId, previousLevels);
			for (final I_C_DunningLevel previousLevel : previousLevels)
			{
				// Search if we already have a candidate for previousLevel
				boolean found = false;
				for (final I_C_Dunning_Candidate candidate : previousCandidates)
				{
					if (!candidate.isDunningDocProcessed())
					{
						logger.info("Skip creating dunning candidate for " + sourceDoc + " because we have an unprocessed previous level");
						return false;
					}

					if (candidate.getC_DunningLevel_ID() == previousLevel.getC_DunningLevel_ID())
					{
						found = true;
						break;
					}
				}

				if (!found)
				{
					logger.info("Skip creating dunning candidate for " + sourceDoc + " because previous sequential level not found: " + previousLevel);
					return false;
				}
			}
		}

		//
		// Make sure we respect the days between dunnings
		final int requiredDaysBetweenDunnings = getRequiredDaysBetweenDunnings(context);
		if (requiredDaysBetweenDunnings > 0)
		{
			if (previousCandidates == null)
			{
				// Previous candidates are loaded if we have sequential dunning.
				// Because we haven't sequential dunning, here we are loading all candidates
				// previousCandidates = dunningDAO.retrieveDunningCandidates(context, tableId, recordId);
				throw new NotImplementedDunningException(NotImplementedDunningException.FEATURE_NonSequentialDunning);
			}
			if (!isDaysBetweenDunningsRespected(context.getDunningDate(), requiredDaysBetweenDunnings, previousCandidates))
			{
				return false;
			}

			logger.info("Skip because days between dunning is not respected");
		}

		return true;
	}