@Override
	public List<PurchaseItem> placeRemotePurchaseOrder(
			@NonNull final Collection<PurchaseCandidate> purchaseCandidates)
	{
		return purchaseCandidates.stream()
				.map(NullVendorGatewayInvoker::createPlainPurchaseOrderItem)
				.collect(ImmutableList.toImmutableList());

	}