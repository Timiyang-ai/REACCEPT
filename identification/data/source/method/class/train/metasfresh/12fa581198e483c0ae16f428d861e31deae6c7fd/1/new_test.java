@Test
	public void testOnSupplyCandidateNewOrChange()
	{
		final BigDecimal olderStockQty = new BigDecimal("11");

		final Candidate olderStockCandidate = Candidate.builder()
				.type(Type.STOCK)
				.clientId(org.getAD_Client_ID())
				.orgId(org.getAD_Org_ID())
				.productId(product.getM_Product_ID())
				.warehouseId(warehouse.getM_Warehouse_ID())
				.quantity(olderStockQty)
				.date(t1)
				.build();
		candidateChangeHandler.addOrUpdateStock(olderStockCandidate);

		final BigDecimal supplyQty = new BigDecimal("23");

		final Candidate candidate = Candidate.builder()
				.type(Type.SUPPLY)
				.clientId(org.getAD_Client_ID())
				.orgId(org.getAD_Org_ID())
				.subType(SubType.PRODUCTION)
				.productId(product.getM_Product_ID())
				.warehouseId(warehouse.getM_Warehouse_ID())
				.quantity(supplyQty)
				.date(t2)
				.build();
		candidateChangeHandler.onSupplyCandidateNewOrChange(candidate);

		final List<I_MD_Candidate> records = DispoTestUtils.retrieveAllRecords();
		assertThat(records.size(), is(3));
		final I_MD_Candidate stockRecord = DispoTestUtils.filter(Type.STOCK, t2).get(0);
		final I_MD_Candidate supplyRecord = DispoTestUtils.filter(Type.SUPPLY).get(0);

		assertThat(supplyRecord.getQty(), comparesEqualTo(supplyQty));
		assertThat(supplyRecord.getMD_Candidate_SubType(), is(SubType.PRODUCTION.toString()));
		assertThat(stockRecord.getQty(), comparesEqualTo(new BigDecimal("34")));
		assertThat(supplyRecord.getMD_Candidate_Parent_ID(), is(stockRecord.getMD_Candidate_ID()));

		assertThat(supplyRecord.getSeqNo(), is(stockRecord.getSeqNo() + 1)); // when we sort by SeqNo, the stock needs to be first and thus have the smaller value
	}