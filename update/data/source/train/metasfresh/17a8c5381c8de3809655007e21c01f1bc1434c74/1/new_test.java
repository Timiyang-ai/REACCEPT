@Test
	public void testOnSupplyCandidateNewOrChange()
	{
		final BigDecimal olderStockQty = new BigDecimal("11");

		final Candidate olderStockCandidate = Candidate.builder()
				.type(Type.STOCK)
				.orgId(1)
				.productId(product.getM_Product_ID())
				.warehouseId(warehouse.getM_Warehouse_ID())
				.quantity(olderStockQty)
				.date(t1)
				.build();
		final Candidate persistedOlderStockCandidate = candidateChangeHandler.updateStock(olderStockCandidate);

		final BigDecimal supplyQty = new BigDecimal("23");

		final Candidate candidate = Candidate.builder()
				.type(Type.SUPPLY)
				.orgId(1)
				.subType(SubType.PRODUCTION)
				.productId(product.getM_Product_ID())
				.warehouseId(warehouse.getM_Warehouse_ID())
				.quantity(supplyQty)
				.date(t2)
				.build();
		candidateChangeHandler.onSupplyCandidateNewOrChange(candidate);

		final List<I_MD_Candidate> records = retriveRecords();
		assertThat(records.size(), is(3));
		final I_MD_Candidate stockRecord = records.stream().filter(r -> r.getMD_Candidate_Type().equals(Type.STOCK.toString()) && r.getMD_Candidate_ID() != persistedOlderStockCandidate.getId()).findFirst().get();
		final I_MD_Candidate supplyRecord = records.stream().filter(r -> r.getMD_Candidate_Type().equals(Type.SUPPLY.toString())).findFirst().get();

		assertThat(supplyRecord.getQty(), comparesEqualTo(supplyQty));
		assertThat(supplyRecord.getMD_Candidate_SubType(), is(SubType.PRODUCTION.toString()));
		assertThat(stockRecord.getQty(), comparesEqualTo(new BigDecimal("34")));
		assertThat(supplyRecord.getMD_Candidate_Parent_ID(), is(stockRecord.getMD_Candidate_ID()));
	}