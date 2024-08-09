@Test
	public void fromCandidate()
	{
		final Timestamp date = new Timestamp(NOW.getTime());

		final Candidate cand = Candidate.builder().type(CandidateType.STOCK)
				.materialDescriptor(createMaterialDescriptor().withDate(date))
				.build();
		final CandidatesQuery query = CandidatesQuery.fromCandidate(cand, false);

		assertThat(query.getMaterialDescriptorQuery().getDate()).isEqualTo(date);
		assertThat(query.getMaterialDescriptorQuery().getDateOperator()).isEqualTo(DateOperator.AT);
		assertThat(query.getMaterialDescriptorQuery().getProductId()).isEqualTo(PRODUCT_ID);
		assertThat(query.getMaterialDescriptorQuery().getStorageAttributesKey()).isEqualTo(STORAGE_ATTRIBUTES_KEY);
		assertThat(query.getMaterialDescriptorQuery().getWarehouseId()).isEqualTo(WAREHOUSE_ID);

		assertThat(query.getType()).isEqualTo(CandidateType.STOCK);
		assertThat(query.getParentId()).isEqualTo(CandidateId.UNSPECIFIED);
	}