private final void save(final PurchaseCandidate purchaseCandidate, final I_C_PurchaseCandidate existingRecord)
	{
		if (existingRecord != null)
		{
			if (existingRecord.isProcessed() && !purchaseCandidate.isProcessed())
			{
				throw new AdempiereException("Purchase candidate was already processed").setParameter("purchaseCandidate", purchaseCandidate);
			}
		}

		I_C_PurchaseCandidate record = existingRecord;
		if (record == null)
		{
			record = newInstance(I_C_PurchaseCandidate.class);
		}

		record.setC_OrderSO_ID(purchaseCandidate.getSalesOrderId());
		record.setC_OrderLineSO_ID(purchaseCandidate.getSalesOrderLineId());
		record.setC_OrderLinePO_ID(purchaseCandidate.getPurchaseOrderLineId());
		record.setAD_Org_ID(purchaseCandidate.getOrgId());
		record.setM_Warehouse_ID(purchaseCandidate.getWarehouseId());
		record.setM_Product_ID(purchaseCandidate.getProductId());
		record.setC_UOM_ID(purchaseCandidate.getUomId());
		record.setVendor_ID(purchaseCandidate.getVendorBPartnerId());
		record.setC_BPartner_Product_ID(purchaseCandidate.getVendorProductInfo().getBPartnerProductId());
		record.setQtyRequiered(purchaseCandidate.getQtyRequired());
		record.setDatePromised(TimeUtil.asTimestamp(purchaseCandidate.getDatePromised()));

		record.setProcessed(purchaseCandidate.isProcessed());

		InterfaceWrapperHelper.save(record);
		purchaseCandidate.markSaved(record.getC_PurchaseCandidate_ID());
	}