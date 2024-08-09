public void execute()
	{
		final HUsForInventoryStrategy strategy = draftInventoryLines.getStrategy();

		// create/update new lines
		final Iterator<I_M_HU> hus = strategy.streamHus().iterator();
		while (hus.hasNext())
		{
			final I_M_HU hu = hus.next();
			seenLocatorIds.add(hu.getM_Locator_ID());

			if (strategy.getMaxLocatorsAllowed() > 0 && strategy.getMaxLocatorsAllowed() < seenLocatorIds.size())
			{
				return;
			}

			countInventoryLines = countInventoryLines + createOrUpdateInventoryLines(hu).count();
		}
	}