public void execute()
	{
		final HUsForInventoryStrategy strategy = draftInventoryLines.getStrategy();

		final Iterator<HuForInventoryLine> hus = strategy.streamHus().iterator();
		while (hus.hasNext())
		{
			final HuForInventoryLine hu = hus.next();
			seenLocatorIds.add(hu.getLocatorId());

			if (strategy.getMaxLocatorsAllowed() > 0 && strategy.getMaxLocatorsAllowed() < seenLocatorIds.size())
			{
				return;
			}

			createOrUpdateInventoryLine(hu);
			countInventoryLines++;
		}
	}