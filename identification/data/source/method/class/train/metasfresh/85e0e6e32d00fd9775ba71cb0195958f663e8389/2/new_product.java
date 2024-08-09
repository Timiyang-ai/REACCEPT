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

		final InventoryRepository inventoryLineRepository = inventoryLinesCreationCtx.getInventoryRepo();
		final InventoryId inventoryId = inventoryLinesCreationCtx.getInventoryId();
		createdOrUpdatedLines
				.values()
				.forEach(line -> inventoryLineRepository.saveInventoryLine(line, inventoryId));

	}