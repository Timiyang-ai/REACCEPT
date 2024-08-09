public static CandidatesQuery fromCandidate(
			@NonNull final Candidate candidate,
			final boolean includeParentId)
	{
		if (!candidate.getId().isNull())
		{
			return CandidatesQuery.fromId(candidate.getId());
		}

		final ProductionDetailsQuery productionDetailsQuery = ProductionDetailsQuery
				.ofProductionDetailOrNull(ProductionDetail.castOrNull(candidate.getBusinessCaseDetail()));

		final DistributionDetailsQuery distributionDetailsQuery = DistributionDetailsQuery
				.ofDistributionDetailOrNull(DistributionDetail.castOrNull(candidate.getBusinessCaseDetail()));

		final PurchaseDetailsQuery purchaseDetailsQuery = PurchaseDetailsQuery
				.ofPurchaseDetailOrNull(PurchaseDetail.castOrNull(candidate.getBusinessCaseDetail()));

		final DemandDetailsQuery demandDetailsQuery = DemandDetailsQuery
				.ofDemandDetailOrNull(DemandDetail.castOrNull(candidate.getBusinessCaseDetail()));

		final MaterialDescriptorQuery materialDescriptorQuery = MaterialDescriptorQuery.forDescriptor(
				candidate.getMaterialDescriptor(),
				DateAndSeqNo.ofCandidate(candidate));

		final CandidatesQueryBuilder builder = CandidatesQuery.builder()
				.materialDescriptorQuery(materialDescriptorQuery)
				.matchExactStorageAttributesKey(true)
				.demandDetailsQuery(demandDetailsQuery)
				.distributionDetailsQuery(distributionDetailsQuery)
				.productionDetailsQuery(productionDetailsQuery)
				.purchaseDetailsQuery(purchaseDetailsQuery)
				.transactionDetails(candidate.getTransactionDetails())
				.groupId(candidate.getGroupId())
				.orgId(candidate.getOrgId())
				//.status(candidate.getStatus())
				.businessCase(candidate.getBusinessCase())
				.type(candidate.getType());

		if (includeParentId)
		{
			builder.parentId(candidate.getParentId());
		}
		else
		{
			builder.parentId(CandidateId.UNSPECIFIED);
		}
		return builder.build();
	}