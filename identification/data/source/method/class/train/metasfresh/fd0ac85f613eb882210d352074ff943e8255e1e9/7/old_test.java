	@Test
	public void save()
	{
		// @formatter:off
		new Expectations()
		{{
			referenceGenerator.getNextDemandReference(); result = "nextDemandReference";
		}}; // @formatter:on

		final PurchaseCandidate purchaseCandidate = PurchaseCandidateTestTool.createPurchaseCandidate(0, Quantity.of(TEN, uom));

		assertThat(purchaseCandidate.getGroupReference().getDemandReference()).isEqualTo(DemandGroupReference.REFERENCE_NOT_YET_SET); // guard

		// invoke the method under test
		final PurchaseCandidateId id = purchaseCandidateRepository.save(purchaseCandidate);

		final List<I_C_PurchaseCandidate> candidateRecords = POJOLookupMap.get().getRecords(I_C_PurchaseCandidate.class);
		// assertThat(candidateRecords).hasSize(1); // there is also the record we created in the init method, so it's >1

		assertThat(candidateRecords)
				.filteredOn(candidateRecord -> candidateRecord.getC_PurchaseCandidate_ID() == id.getRepoId())
				.hasSize(1)
				.allSatisfy(candidateRecord -> {
					assertThat(candidateRecord.getC_PurchaseCandidate_ID()).isEqualTo(id.getRepoId());
					assertThat(candidateRecord.getDemandReference()).isEqualTo("nextDemandReference");
				});
	}