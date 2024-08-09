	@Test
	public void test_load_to_reversal()
	{
		//
		// Create incoming
		final I_M_Transaction mtrx = helper.createMTransaction(
				X_M_Transaction.MOVEMENTTYPE_VendorReceipts,
				pTomato,
				new BigDecimal(23));
		POJOWrapper.setInstanceName(mtrx, "Incoming trx");

		// // create and destroy instances only with a I_M_Transaction
		// // final List<I_M_HU> huPalets =
		// trxBL.transferIncomingToHUs(mtrx, huDefPalet);

		//
		// Create incoming mtrx reversal
		final I_M_Transaction mtrxReversal = helper.createMTransactionReversal(mtrx);
		POJOWrapper.setInstanceName(mtrxReversal, "Incoming trx reversal");
		Assert.assertThat("Reversal qty shall be original qty negated",
				mtrxReversal.getMovementQty(),
				Matchers.comparesEqualTo(mtrx.getMovementQty().negate()));

		//
		// Create incoming "source" and validate
		final MTransactionAllocationSourceDestination mtrxSource = new MTransactionAllocationSourceDestination(mtrx);
		StaticHUAssert.assertStorageLevels(mtrxSource.getStorage(), "23", "23", "0"); // Qty Total/Allocated/Available

		//
		// Create reversal "destination" and validate
		final MTransactionAllocationSourceDestination mtrxReversalDestination = new MTransactionAllocationSourceDestination(mtrxReversal);
		StaticHUAssert.assertStorageLevels(mtrxReversalDestination.getStorage(), "23", "0", "23"); // Qty Total/Allocated/Available

		final IMutableHUContext huContext = helper.getHUContext();

		final HULoader loader = HULoader.of(mtrxSource, mtrxReversalDestination);

		final IAllocationRequest request = AllocationUtils.createQtyRequest(
				huContext,
				mtrx.getM_Product(),
				mtrx.getMovementQty(),
				Services.get(IHandlingUnitsBL.class).getC_UOM(mtrx),
				helper.getTodayZonedDateTime(),
				mtrx);

		// dumpStatusAfterTest = true;
		loader.load(request);

		//
		// Reload mtrx storage and validate it
		{
			final IProductStorage mtrxStorage2 = new MTransactionAllocationSourceDestination(mtrx)
					.getStorage();

			StaticHUAssert.assertStorageLevels(mtrxStorage2, "23", "0", "23"); // Total/Qty/Free
		}

		//
		// Reload mtrx storage reversal and validate it
		{
			final IProductStorage mtrxReversalStorage2 = new MTransactionAllocationSourceDestination(mtrxReversal)
					.getStorage();

			StaticHUAssert.assertStorageLevels(mtrxReversalStorage2, "23", "23", "0"); // Total/Qty/Free
		}

		StaticHUAssert.assertAllStoragesAreValid();
	}