	@Test
	public void placeRemotePurchaseOrder()
	{
		final I_C_UOM EACH = createUOM("Ea");

		final PurchaseCandidate purchaseCandidate = PurchaseCandidate.builder()
				.groupReference(DemandGroupReference.EMPTY)
				.orgId(OrgId.ofRepoId(10))
				.warehouseId(WarehouseId.ofRepoId(60))
				.purchaseDatePromised(SystemTime.asZonedDateTime())
				.vendorId(BPartnerId.ofRepoId(30))
				.productId(ProductId.ofRepoId(20))
				.attributeSetInstanceId(AttributeSetInstanceId.ofRepoId(21))
				.vendorProductNo("vendorProductNo_20")
				.qtyToPurchase(Quantity.of(TEN, EACH))
				.salesOrderAndLineIdOrNull(OrderAndLineId.ofRepoIds(40, 50))
				.profitInfoOrNull(PurchaseCandidateTestTool.createPurchaseProfitInfo())
				.build();

		final List<PurchaseItem> purchaseItems = NullVendorGatewayInvoker.INSTANCE.placeRemotePurchaseOrder(ImmutableList.of(purchaseCandidate));

		assertThat(purchaseItems).hasSize(1);
		assertThat(purchaseItems.get(0)).isInstanceOf(PurchaseOrderItem.class);

		final PurchaseOrderItem purchaseOrderItem = (PurchaseOrderItem)purchaseItems.get(0);
		assertThat(purchaseOrderItem.getRemotePurchaseOrderId()).isEqualTo(NullVendorGatewayInvoker.NO_REMOTE_PURCHASE_ID);
	}