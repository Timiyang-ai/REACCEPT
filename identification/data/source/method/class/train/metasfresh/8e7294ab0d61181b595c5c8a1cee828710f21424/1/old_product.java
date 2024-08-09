public void cuToExistingTU(
			final I_M_HU cuHU,
			final BigDecimal qtyCU,
			final I_M_HU tuHU)
	{
		Preconditions.checkNotNull(cuHU, "Param 'cuHU' may not be null");
		Preconditions.checkNotNull(qtyCU, "Param 'qtyCU' may not be null");

		final IHandlingUnitsBL handlingUnitsBL = Services.get(IHandlingUnitsBL.class);
		final IHandlingUnitsDAO handlingUnitsDAO = Services.get(IHandlingUnitsDAO.class);

		final IAllocationDestination destination;
		if (handlingUnitsBL.isAggregateHU(tuHU))
		{
			// we will load directly to the given tuHU which is actually a VHU
			destination = HUListAllocationSourceDestination.of(tuHU);
		}
		else
		{
			// we are later going to attach something as a child to the given 'tuHU'
			if (qtyCU.compareTo(getMaximumQtyCU(cuHU)) >= 0)
			{
				// we will attach the whole cuHU to tuHU and thus not load/split anything
				destination = null;
			}
			else
			{
				// we will load qtCU do a new VHU (a new CU) and then attach that new CU to 'tuHU'
				destination = HUProducerDestination.ofVirtualPI();
			}
		}

		// get cuHU's old parent (if any) for later usage, before the changes start
		final I_M_HU oldParentTU = handlingUnitsDAO.retrieveParent(tuHU);
		final IHUProductStorage singleProductStorage = getSingleProductStorage(cuHU);

		if (destination != null)
		{
			HUSplitBuilderCoreEngine
					.of(
							huContext,
							cuHU,
							// forceAllocation = true; we don't want to get bothered by capacity constraint, even if the destination *probably* doesn't have any to start with
							huContext -> createCUAllocationRequest(huContext,
									singleProductStorage.getM_Product(),
									singleProductStorage.getC_UOM(),
									qtyCU,
									true),
							destination)
					.withPropagateHUValues()
					.withAllowPartialUnloads(true) // we allow partial loads and unloads so if a user enters a very large number, then that will just account to "all of it" and there will be no error
					.performSplit();
		}

		if (handlingUnitsBL.isAggregateHU(tuHU))
		{
			return; // we are done; no attaching
		}

		// we attach the
		final List<I_M_HU> childCUs;
		if (destination == null)
		{
			childCUs = ImmutableList.of(cuHU);
		}
		else
		{
			childCUs = ((HUProducerDestination)destination).getCreatedHUs(); // i think there will be just one, but no need to bother

		}

		// get *the* MI HU_Item of 'tuHU'. There must be exactly one, otherwise, tuHU wouldn't exist here in the first place.
		final List<I_M_HU_Item> tuMaterialItem = handlingUnitsDAO.retrieveItems(tuHU)
				.stream()
				.filter(piItem -> X_M_HU_PI_Item.ITEMTYPE_Material.equals(piItem.getItemType()))
				.collect(Collectors.toList());
		Check.errorUnless(tuMaterialItem.size() == 1, "Param 'tuHU' does not have one 'MI' item; tuHU={}", tuHU);

		// finally do the attaching
		childCUs.forEach(newChildCU -> {
			setParent(newChildCU,
					tuMaterialItem.get(0),
					localHuContext -> {
						final IHUDocumentFactoryService huDocumentFactoryService = Services.get(IHUDocumentFactoryService.class);
						for (final TableRecordReference ref : referencedObjects)
						{
							final List<IHUDocument> huDocuments = huDocumentFactoryService.createHUDocuments(localHuContext.getCtx(), ref.getTableName(), ref.getRecord_ID());
							for (final IHUDocument huDocument : huDocuments)
							{
								final boolean huDocumentBelongsToCuHU = huDocument.getAssignedHandlingUnits().stream().anyMatch(hu -> hu.getM_HU_ID() == cuHU.getM_HU_ID());
								if (!huDocumentBelongsToCuHU)
								{
									continue;
								}
								for (final IHUDocumentLine huDocumentLine : huDocument.getLines())
								{
									final IHUAllocations huAllocations = huDocumentLine.getHUAllocations();
									huAllocations.allocate(null, oldParentTU, cuHU, qtyCU.negate(), singleProductStorage.getC_UOM(), false);
									huAllocations.allocate(null, tuHU, newChildCU, qtyCU, singleProductStorage.getC_UOM(), false);
								}
							}
						}
					});
		});
	}