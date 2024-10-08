@Test
	public void testApplyDeltaToLaterStockCandidates()
	{
		final Candidate earlierCandidate;
		final Candidate candidate;
		final Candidate evenLaterCandidate;
		final Candidate evenLaterCandidateWithDifferentWarehouse;

		// preparations
		{
			final MaterialDescriptor materialDescr = MaterialDescriptor.builder()
					.productId(product.getM_Product_ID())
					.warehouseId(warehouse.getM_Warehouse_ID())
					.quantity(new BigDecimal("10"))
					.date(t2)
					.build();

			candidate = Candidate.builder()
					.type(Type.STOCK)
					.clientId(org.getAD_Client_ID())
					.orgId(org.getAD_Org_ID())
					.materialDescr(materialDescr)
					.build();
			candidateRepository.addOrUpdateOverwriteStoredSeqNo(candidate);

			final MaterialDescriptor earlierMaterialDescr = materialDescr.withDate(t1);

			earlierCandidate = candidateRepository
					.addOrUpdateOverwriteStoredSeqNo(Candidate.builder()
							.type(Type.STOCK)
							.clientId(org.getAD_Client_ID())
							.orgId(org.getAD_Org_ID())
							.materialDescr(earlierMaterialDescr)
							.build());

			final MaterialDescriptor laterMaterialDescr = materialDescr.withDate(t3);

			final Candidate laterCandidate = Candidate.builder()
					.type(Type.STOCK)
					.clientId(org.getAD_Client_ID())
					.orgId(org.getAD_Org_ID())
					.materialDescr(laterMaterialDescr)
					.build();
			candidateRepository.addOrUpdateOverwriteStoredSeqNo(laterCandidate);

			final MaterialDescriptor evenLatermaterialDescr = materialDescr
					.withQuantity(new BigDecimal("12"))
					.withDate(t4);

			evenLaterCandidate = Candidate.builder()
					.type(Type.STOCK)
					.clientId(org.getAD_Client_ID())
					.orgId(org.getAD_Org_ID())
					.materialDescr(evenLatermaterialDescr)
					.build();
			candidateRepository.addOrUpdateOverwriteStoredSeqNo(evenLaterCandidate);

			final MaterialDescriptor evenLatermaterialDescrWithDifferentWarehouse = evenLatermaterialDescr
					.withWarehouseId(otherWarehouse.getM_Warehouse_ID());

			evenLaterCandidateWithDifferentWarehouse = Candidate.builder()
					.type(Type.STOCK)
					.clientId(org.getAD_Client_ID())
					.orgId(org.getAD_Org_ID())
					.materialDescr(evenLatermaterialDescrWithDifferentWarehouse)
					.build();
			candidateRepository.addOrUpdateOverwriteStoredSeqNo(evenLaterCandidateWithDifferentWarehouse);
		}

		// do the test
		stockCandidateService.applyDeltaToLaterStockCandidates(
				product.getM_Product_ID(),
				warehouse.getM_Warehouse_ID(),
				t2,
				earlierCandidate.getGroupId(),
				new BigDecimal("3"));

		// assert that every stock record got some groupId
		DispoTestUtils.retrieveAllRecords().forEach(r -> assertThat(r.getMD_Candidate_GroupId(), greaterThan(0)));

		final Optional<Candidate> earlierCandidateAfterChange = candidateRepository.retrieveLatestMatch(mkStockUntilSegment(t1, warehouse));
		assertThat(earlierCandidateAfterChange.isPresent(), is(true));
		assertThat(earlierCandidateAfterChange.get().getQuantity(), is(earlierCandidate.getQuantity())); // quantity shall be unchanged
		assertThat(earlierCandidateAfterChange.get().getGroupId(), is(earlierCandidate.getGroupId())); // basically the same candidate

		final I_MD_Candidate candidateRecordAfterChange = DispoTestUtils.filter(Type.STOCK, t2).get(0); // candidateRepository.retrieveExact(candidate).get();
		assertThat(candidateRecordAfterChange.getQty(), is(new BigDecimal("10"))); // quantity shall be unchanged, because that method shall only update *later* records
		assertThat(candidateRecordAfterChange.getMD_Candidate_GroupId(), not(is(earlierCandidate.getGroupId())));

		final Optional<Candidate> laterCandidateAfterChange = candidateRepository.retrieveLatestMatch(mkStockUntilSegment(t3, warehouse));
		assertThat(laterCandidateAfterChange.isPresent(), is(true));
		assertThat(laterCandidateAfterChange.get().getQuantity(), is(new BigDecimal("13"))); // quantity shall be plus 3
		assertThat(laterCandidateAfterChange.get().getGroupId(), is(earlierCandidate.getGroupId()));

		final I_MD_Candidate evenLaterCandidateRecordAfterChange = DispoTestUtils.filter(Type.STOCK, t4, product.getM_Product_ID(), warehouse.getM_Warehouse_ID()).get(0); // candidateRepository.retrieveExact(evenLaterCandidate).get();
		assertThat(evenLaterCandidateRecordAfterChange.getQty(), is(new BigDecimal("15"))); // quantity shall be plus 3 too
		assertThat(evenLaterCandidateRecordAfterChange.getMD_Candidate_GroupId(), is(earlierCandidate.getGroupId()));

		final I_MD_Candidate evenLaterCandidateWithDifferentWarehouseAfterChange = DispoTestUtils.filter(Type.STOCK, t4, product.getM_Product_ID(), otherWarehouse.getM_Warehouse_ID()).get(0); // candidateRepository.retrieveExact(evenLaterCandidateWithDifferentWarehouse).get();
		assertThat(evenLaterCandidateWithDifferentWarehouseAfterChange.getQty(), is(new BigDecimal("12"))); // quantity shall be unchanged, because we changed another warehouse and this one should not have been matched
		assertThat(evenLaterCandidateWithDifferentWarehouseAfterChange.getMD_Candidate_GroupId(), not(is(earlierCandidate.getGroupId())));

	}