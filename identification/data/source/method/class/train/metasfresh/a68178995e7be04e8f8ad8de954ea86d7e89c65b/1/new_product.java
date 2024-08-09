private final void allocate()
	{
		// Make sure we did not run "allocate" before
		// i.e. this builder is still configurable
		assertConfigurable();

		//
		// Get the HUs which we need to allocate to shipment schedules
		// and make sure they are ok.
		assertFromHUsOutOfTrx();

		//
		// Make sure we have remaining qty to pack
		if (!hasRemainingQtyToPack())
		{
			return;
		}

		//
		// Allocate
		final IHUContext huContextInitial = createHUContextInitial();
		huTrxBL.createHUContextProcessorExecutor(huContextInitial)
				.run(this::allocate0);
	}