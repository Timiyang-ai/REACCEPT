private final void save(final PurchaseCandidate purchaseCandidate, final I_C_PurchaseCandidate existingRecord)
	{
		I_C_PurchaseCandidate record = existingRecord;
		if (record == null)
		{
			record = newInstance(I_C_PurchaseCandidate.class);
		}

		record.setC_OrderLineSO_ID(purchaseCandidate.getSalesOrderLineId());
		record.setM_Product_ID(purchaseCandidate.getProductId());
		record.setC_UOM_ID(purchaseCandidate.getUomId());
		record.setVendor_ID(purchaseCandidate.getVendorBPartnerId());
		record.setQtyRequiered(purchaseCandidate.getQtyRequired());
		record.setDatePromised(TimeUtil.asTimestamp(purchaseCandidate.getDatePromised()));
		record.setProcessed(purchaseCandidate.isProcessed());

		InterfaceWrapperHelper.save(record);

		purchaseCandidate.setRepoId(record.getC_PurchaseCandidate_ID());
	}