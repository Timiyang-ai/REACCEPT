@Test
	public void applyDeltaToMatchingLaterStockCandidates()
	{
		final Candidate earlierCandidate;
		final Candidate evenLaterCandidate;
		final Candidate evenLaterCandidateWithDifferentWarehouse;

		// preparations
		{
			final MaterialDescriptor materialDescriptor = createAndAddStock(t2);

			final MaterialDescriptor earlierMaterialDescriptor = materialDescriptor.withDate(t1);

			earlierCandidate = candidateRepositoryCommands
					.addOrUpdateOverwriteStoredSeqNo(Candidate.builder()
							.type(CandidateType.STOCK)
							.clientId(CLIENT_ID)
							.orgId(ORG_ID)
							.materialDescriptor(earlierMaterialDescriptor)
							.build())
					.getCandidate();

			final MaterialDescriptor laterMaterialDescriptor = materialDescriptor.withDate(t3);

			final Candidate laterCandidate = Candidate.builder()
					.type(CandidateType.STOCK)
					.clientId(CLIENT_ID)
					.orgId(ORG_ID)
					.materialDescriptor(laterMaterialDescriptor)
					.build();
			candidateRepositoryCommands.addOrUpdateOverwriteStoredSeqNo(laterCandidate);

			final MaterialDescriptor evenLatermaterialDescriptor = materialDescriptor
					.withQuantity(new BigDecimal("12"))
					.withDate(t4);

			evenLaterCandidate = Candidate.builder()
					.type(CandidateType.STOCK)
					.clientId(CLIENT_ID)
					.orgId(ORG_ID)
					.materialDescriptor(evenLatermaterialDescriptor)
					.build();
			candidateRepositoryCommands.addOrUpdateOverwriteStoredSeqNo(evenLaterCandidate);

			final MaterialDescriptor evenLatermaterialDescrWithDifferentWarehouse = evenLatermaterialDescriptor
					.withWarehouseId(OTHER_WAREHOUSE_ID);

			evenLaterCandidateWithDifferentWarehouse = Candidate.builder()
					.type(CandidateType.STOCK)
					.clientId(CLIENT_ID)
					.orgId(ORG_ID)
					.materialDescriptor(evenLatermaterialDescrWithDifferentWarehouse)
					.build();
			candidateRepositoryCommands.addOrUpdateOverwriteStoredSeqNo(evenLaterCandidateWithDifferentWarehouse);
		}

		// do the test
		final MaterialDescriptor materialDescriptor = MaterialDescriptor.builder()
				.productDescriptor(createProductDescriptor())
				.warehouseId(WAREHOUSE_ID)
				.date(t2)
				.quantity(THREE)
				.build();
		final Candidate candidateWithDelta = Candidate.builder()
				.type(CandidateType.STOCK)
				.clientId(CLIENT_ID)
				.orgId(ORG_ID)
				.materialDescriptor(materialDescriptor)
				.groupId(earlierCandidate.getGroupId()).build();
		stockCandidateService.applyDeltaToMatchingLaterStockCandidates(SaveResult.builder().candidate(candidateWithDelta).build());

		// assert that every stock record got some groupId
		assertThat(DispoTestUtils.retrieveAllRecords()).allSatisfy(r -> assertThatModel(r).hasValueGreaterThanZero(I_MD_Candidate.COLUMN_MD_Candidate_GroupId));
		{
			final Candidate earlierCandidateAfterChange = candidateRepositoryRetrieval.retrieveLatestMatchOrNull(mkQueryForStockUntilDate(t1, WAREHOUSE_ID));
			assertThat(earlierCandidateAfterChange).as("Expected canddiate with Date=<%s> and warehouseId=<%s> to exist", t1, WAREHOUSE_ID).isNotNull();
			assertThat(earlierCandidateAfterChange.getQuantity()).isEqualTo(earlierCandidate.getQuantity()); // quantity shall be unchanged
			assertThat(earlierCandidateAfterChange.getGroupId()).isEqualTo(earlierCandidate.getGroupId()); // basically the same candidate

			final I_MD_Candidate candidateRecordAfterChange = DispoTestUtils.filter(CandidateType.STOCK, t2).get(0); // candidateRepository.retrieveExact(candidate).get();
			assertThat(candidateRecordAfterChange.getQty()).isEqualByComparingTo("10"); // quantity shall be unchanged, because that method shall only update *later* records
			assertThat(candidateRecordAfterChange.getMD_Candidate_GroupId()).isNotEqualTo(earlierCandidate.getGroupId());
		}
		{
			final Candidate laterCandidateAfterChange = candidateRepositoryRetrieval.retrieveLatestMatchOrNull(mkQueryForStockUntilDate(t3, WAREHOUSE_ID));
			assertThat(laterCandidateAfterChange).isNotNull();
			assertThat(laterCandidateAfterChange.getQuantity()).isEqualByComparingTo("13"); // quantity shall be plus 3
			assertThat(laterCandidateAfterChange.getGroupId()).isEqualTo(earlierCandidate.getGroupId());
		}
		{
			final I_MD_Candidate evenLaterCandidateRecordAfterChange = DispoTestUtils.filter(CandidateType.STOCK, t4, PRODUCT_ID, WAREHOUSE_ID).get(0); // candidateRepository.retrieveExact(evenLaterCandidate).get();
			assertThat(evenLaterCandidateRecordAfterChange.getQty()).isEqualByComparingTo("15"); // quantity shall be plus 3 too
			assertThat(evenLaterCandidateRecordAfterChange.getMD_Candidate_GroupId()).isEqualTo(earlierCandidate.getGroupId());
		}
		{
			final I_MD_Candidate evenLaterCandidateWithDifferentWarehouseAfterChange = DispoTestUtils.filter(CandidateType.STOCK, t4, PRODUCT_ID, OTHER_WAREHOUSE_ID).get(0); // candidateRepository.retrieveExact(evenLaterCandidateWithDifferentWarehouse).get();
			assertThat(evenLaterCandidateWithDifferentWarehouseAfterChange.getQty()).isEqualByComparingTo("12"); // quantity shall be unchanged, because we changed another warehouse and this one should not have been matched
			assertThat(evenLaterCandidateWithDifferentWarehouseAfterChange.getMD_Candidate_GroupId(), not(is(earlierCandidate.getGroupId())));
		}
	}