public void execute()
	{
		final HUsForInventoryStrategy strategy = inventoryLinesCreationCtx.getStrategy();

		final Iterator<HuForInventoryLine> hus = strategy.streamHus().iterator();
		while (hus.hasNext())
		{
			final HuForInventoryLine hu = hus.next();
			seenLocatorIds.add(hu.getLocatorId());

			if (strategy.getMaxLocatorsAllowed() > 0 && strategy.getMaxLocatorsAllowed() < seenLocatorIds.size())
			{
				break;
			}

			createOrUpdateInventoryLine(hu);
			countInventoryLines++;
		}

		final InventoryLineRepository inventoryLineRepository = inventoryLinesCreationCtx.getInventoryLineRepository();
		createdOrUpdatedLines
				.values()
				.forEach(inventoryLineRepository::save);

	}