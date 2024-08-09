	@Test
	void execute_SingleHUInventoryLineAggregator()
	{
		final InventoryId inventoryId = createInventoryRecord(AggregationType.SINGLE_HU.getDocBaseAndSubType());
		final InventoryLinesCreationCtx ctx = createContext(
				inventoryId,
				this::createAndStreamTestHUs,
				SingleHUInventoryLineAggregator.INSTANCE);

		// execute the method under test
		new DraftInventoryLinesCreator(ctx).execute();

		final Inventory result = inventoryRepo.getById(inventoryId);
		expect(result).toMatchSnapshot();
	}