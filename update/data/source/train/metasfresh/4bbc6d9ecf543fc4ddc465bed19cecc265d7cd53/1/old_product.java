public List<I_M_HU> cuToExistingTU(
			@NonNull final I_M_HU sourceCuHU,
			@NonNull final Quantity qtyCU,
			@NonNull final I_M_HU targetTuHU)
	{
		// NOTE: because this method does several non-atomic operations it is important to glue them together in a transaction
		return trxManager.call(ITrx.TRXNAME_ThreadInherited, () -> cuToExistingTU_InTrx(sourceCuHU, qtyCU, targetTuHU));
	}