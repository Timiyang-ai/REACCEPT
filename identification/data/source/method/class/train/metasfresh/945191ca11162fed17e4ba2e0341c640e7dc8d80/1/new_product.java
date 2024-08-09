@Override
	public List<RemotePurchaseItem> placeRemotePurchaseOrder(
			@NonNull final Collection<PurchaseCandidate> purchaseCandidates)
	{
		return purchaseCandidates.stream()
				.map(NullVendorGatewayInvoker::createPlainPurchaseOrderItem)
				.collect(ImmutableList.toImmutableList());

	}