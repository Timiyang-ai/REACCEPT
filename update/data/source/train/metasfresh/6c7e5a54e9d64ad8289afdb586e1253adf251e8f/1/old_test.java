@Test
	public void testApplyDeltaToLaterStockCandidates()
	{
		final Candidate earlierCandidate;
		final Candidate candidate;
		final Candidate evenLaterCandidate;
		final Candidate evenLaterCandidateWithDifferentWarehouse;

		// preparations
		{
			final MaterialDescriptor materialDescriptor = MaterialDescriptor.builder()
					.complete(true)
					.productDescriptor(createProductDescriptor())
					.warehouseId(WAREHOUSE_ID)
					.quantity(new BigDecimal("10"))
					.date(t2)
					.build();

			candidate = Candidate.builder()
					.type(CandidateType.STOCK)
					.clientId(org.getAD_Client_ID())
					.orgId(org.getAD_Org_ID())
					.materialDescriptor(materialDescriptor)
					.build();
			candidateRepositoryCommands.addOrUpdateOverwriteStoredSeqNo(candidate);

			final MaterialDescriptor earlierMaterialDescriptor = materialDescriptor.withDate(t1);

			earlierCandidate = candidateRepositoryCommands
					.addOrUpdateOverwriteStoredSeqNo(Candidate.builder()
							.type(CandidateType.STOCK)
							.clientId(org.getAD_Client_ID())
							.orgId(org.getAD_Org_ID())
							.materialDescriptor(earlierMaterialDescriptor)
							.build());

			final MaterialDescriptor laterMaterialDescriptor = materialDescriptor.withDate(t3);

			final Candidate laterCandidate = Candidate.builder()
					.type(CandidateType.STOCK)
					.clientId(org.getAD_Client_ID())
					.orgId(org.getAD_Org_ID())
					.materialDescriptor(laterMaterialDescriptor)
					.build();
			candidateRepositoryCommands.addOrUpdateOverwriteStoredSeqNo(laterCandidate);

			final MaterialDescriptor evenLatermaterialDescriptor = materialDescriptor
					.withQuantity(new BigDecimal("12"))
					.withDate(t4);

			evenLaterCandidate = Candidate.builder()
					.type(CandidateType.STOCK)
					.clientId(org.getAD_Client_ID())
					.orgId(org.getAD_Org_ID())
					.materialDescriptor(evenLatermaterialDescriptor)
					.build();
			candidateRepositoryCommands.addOrUpdateOverwriteStoredSeqNo(evenLaterCandidate);

			final MaterialDescriptor evenLatermaterialDescrWithDifferentWarehouse = evenLatermaterialDescriptor
					.withWarehouseId(OTHER_WAREHOUSE_ID);

			evenLaterCandidateWithDifferentWarehouse = Candidate.builder()
					.type(CandidateType.STOCK)
					.clientId(org.getAD_Client_ID())
					.orgId(org.getAD_Org_ID())
					.materialDescriptor(evenLatermaterialDescrWithDifferentWarehouse)
					.build();
			candidateRepositoryCommands.addOrUpdateOverwriteStoredSeqNo(evenLaterCandidateWithDifferentWarehouse);
		}

		// do the test
		stockCandidateService.applyDeltaToLaterStockCandidates(
				createProductDescriptor(),
				WAREHOUSE_ID,
				t2,
				earlierCandidate.getGroupId(),
				new BigDecimal("3"));

		// assert that every stock record got some groupId
		DispoTestUtils.retrieveAllRecords().forEach(r -> assertThat(r.getMD_Candidate_GroupId(), greaterThan(0)));

		final Candidate earlierCandidateAfterChange = candidateRepository.retrieveLatestMatchOrNull(mkStockUntilSegment(t1, WAREHOUSE_ID));
		assertThat(earlierCandidateAfterChange).isNotNull();
		assertThat(earlierCandidateAfterChange.getQuantity()).isEqualTo(earlierCandidate.getQuantity()); // quantity shall be unchanged
		assertThat(earlierCandidateAfterChange.getGroupId()).isEqualTo(earlierCandidate.getGroupId()); // basically the same candidate

		final I_MD_Candidate candidateRecordAfterChange = DispoTestUtils.filter(CandidateType.STOCK, t2).get(0); // candidateRepository.retrieveExact(candidate).get();
		assertThat(candidateRecordAfterChange.getQty()).isEqualByComparingTo("10"); // quantity shall be unchanged, because that method shall only update *later* records
		assertThat(candidateRecordAfterChange.getMD_Candidate_GroupId(), not(is(earlierCandidate.getGroupId())));

		final Candidate laterCandidateAfterChange = candidateRepository.retrieveLatestMatchOrNull(mkStockUntilSegment(t3, WAREHOUSE_ID));
		assertThat(laterCandidateAfterChange).isNotNull();
		assertThat(laterCandidateAfterChange.getQuantity()).isEqualByComparingTo("13"); // quantity shall be plus 3
		assertThat(laterCandidateAfterChange.getGroupId()).isEqualTo(earlierCandidate.getGroupId());

		final I_MD_Candidate evenLaterCandidateRecordAfterChange = DispoTestUtils.filter(CandidateType.STOCK, t4, PRODUCT_ID, WAREHOUSE_ID).get(0); // candidateRepository.retrieveExact(evenLaterCandidate).get();
		assertThat(evenLaterCandidateRecordAfterChange.getQty()).isEqualByComparingTo("15"); // quantity shall be plus 3 too
		assertThat(evenLaterCandidateRecordAfterChange.getMD_Candidate_GroupId()).isEqualTo(earlierCandidate.getGroupId());

		final I_MD_Candidate evenLaterCandidateWithDifferentWarehouseAfterChange = DispoTestUtils.filter(CandidateType.STOCK, t4, PRODUCT_ID, OTHER_WAREHOUSE_ID).get(0); // candidateRepository.retrieveExact(evenLaterCandidateWithDifferentWarehouse).get();
		assertThat(evenLaterCandidateWithDifferentWarehouseAfterChange.getQty()).isEqualByComparingTo("12"); // quantity shall be unchanged, because we changed another warehouse and this one should not have been matched
		assertThat(evenLaterCandidateWithDifferentWarehouseAfterChange.getMD_Candidate_GroupId(), not(is(earlierCandidate.getGroupId())));

	}