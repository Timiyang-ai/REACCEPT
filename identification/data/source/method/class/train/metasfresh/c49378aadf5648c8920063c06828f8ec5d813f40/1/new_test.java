	@Test
	public void onCandidateNewOrChange()
	{
		final BigDecimal olderStockQty = ELEVEN;

		final MaterialDescriptor olderMaterialDescriptor = MaterialDescriptor.builder()
				.productDescriptor(createProductDescriptor())
				.warehouseId(WAREHOUSE_ID)
				.quantity(olderStockQty)
				.date(NOW)
				.build();

		final Candidate olderStockCandidate = Candidate.builder()
				.type(CandidateType.STOCK)
				.clientAndOrgId(CLIENT_AND_ORG_ID)
				.materialDescriptor(olderMaterialDescriptor)
				.build();
		candidateRepositoryWriteService.addOrUpdateOverwriteStoredSeqNo(olderStockCandidate);

		final MaterialDescriptor materialDescriptoriptor = MaterialDescriptor.builder()
				.productDescriptor(createProductDescriptor())
				.warehouseId(WAREHOUSE_ID)
				.quantity(TWENTY_THREE)
				.date(AFTER_NOW)
				.build();

		final Candidate candidate = Candidate.builder()
				.type(CandidateType.SUPPLY)
				.clientAndOrgId(CLIENT_AND_ORG_ID)
				.materialDescriptor(materialDescriptoriptor)
				.businessCase(CandidateBusinessCase.PRODUCTION)
				.build();
		supplyCandiateHandler.onCandidateNewOrChange(candidate);

		final List<I_MD_Candidate> records = retrieveAllRecords();
		assertThat(records).hasSize(3);
		final I_MD_Candidate stockRecord = filter(CandidateType.STOCK, AFTER_NOW).get(0);
		final I_MD_Candidate supplyRecord = filter(CandidateType.SUPPLY).get(0);

		assertThat(supplyRecord.getQty()).isEqualByComparingTo(TWENTY_THREE);
		assertThat(supplyRecord.getMD_Candidate_BusinessCase()).isEqualTo(CandidateBusinessCase.PRODUCTION.toString());
		assertThat(stockRecord.getQty()).isEqualByComparingTo(ELEVEN.add(TWENTY_THREE));

		// note that now, the stock record shall have the same SeqNo as it's "actual" record
		assertThat(supplyRecord.getSeqNo()).isEqualTo(stockRecord.getSeqNo());
	}