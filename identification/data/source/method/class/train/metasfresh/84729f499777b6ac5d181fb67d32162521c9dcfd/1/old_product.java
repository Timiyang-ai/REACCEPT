public IAllocationResult load(final IAllocationRequest request)
	{
		//
		// Check if our request is valid
		Check.assumeNotNull(request, "unloadRequest not null");
		if (request.getQty().signum() == 0)
		{
			// Zero Qty Request => nothing to do
			return AllocationUtils.nullResult();
		}

		final IHUContext huContextInitial = request.getHUContext();
		return processInHUContext(huContextInitial, new IHUContextProcessor()
		{
			@Override
			public IMutableAllocationResult process(final IHUContext huContext)
			{
				//
				// Create the new allocation request, identical with given one, but the concept is with given transaction
				final IAllocationRequest unloadRequestInLocalTrx = AllocationUtils.derive(request)
						.setHUContext(huContext)
						.create();

				return unloadSourceThenLoadDestination(unloadRequestInLocalTrx);
			}
		});
	}