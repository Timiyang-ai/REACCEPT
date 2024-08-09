	@Test
	public void createOneOrTwoCandidatesWithChangedTransactionDetailAndQuantity()
	{
		final CandidateChangeService candidateChangeHandler = new CandidateChangeService(ImmutableList.of());
		final CandidateRepositoryRetrieval candidateRepository = new CandidateRepositoryRetrieval();

		final TransactionEventHandler transactionEventHandler = new TransactionEventHandler(
				candidateChangeHandler,
				candidateRepository,
				Mockito.mock(PostMaterialEventService.class));

		final MaterialDescriptor materialDescriptor = MaterialDescriptor.builder()
				.productDescriptor(createProductDescriptor())
				.warehouseId(WAREHOUSE_ID)
				.quantity(new BigDecimal("23"))
				.date(AFTER_NOW)
				.build();

		final Candidate candidate = Candidate.builder()
				.id(CandidateId.ofRepoId(100))
				.parentId(CandidateId.ofRepoId(200))
				.type(CandidateType.SUPPLY)
				.clientAndOrgId(CLIENT_AND_ORG_ID)
				.materialDescriptor(materialDescriptor)
				.businessCase(CandidateBusinessCase.PURCHASE)
				.build();

		final TransactionDetail transactionDetail = TransactionDetail.builder()
				.quantity(new BigDecimal("23"))
				.storageAttributesKey(AttributesKey.ofAttributeValueIds(10))
				.attributeSetInstanceId(20)
				.transactionId(30)
				.transactionDate(AFTER_NOW)
				.complete(true)
				.build();

		// invoke the method under test
		final List<Candidate> result = transactionEventHandler.createOneOrTwoCandidatesWithChangedTransactionDetailAndQuantity(candidate, transactionDetail);
		assertThat(result).hasSize(2);

		assertThat(result.get(1).getId()).isEqualTo(candidate.getId());
		assertThat(result.get(1).getParentId()).isEqualTo(candidate.getParentId());
		assertThat(result.get(1).getQuantity()).isEqualTo(ZERO);

		assertThat(result.get(0).getId()).isEqualTo(CandidateId.NULL);
		assertThat(result.get(0).getParentId()).isEqualTo(CandidateId.NULL);
		assertThat(result.get(0).getQuantity()).isEqualByComparingTo("23");
	}