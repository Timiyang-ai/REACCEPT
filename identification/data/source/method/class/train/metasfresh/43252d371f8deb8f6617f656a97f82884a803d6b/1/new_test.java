	@Test
	public void fromCandidate()
	{
		final Candidate cand = Candidate.builder()
				.clientAndOrgId(ClientAndOrgId.ofClientAndOrg(1, 1))
				.type(CandidateType.STOCK)
				.materialDescriptor(createMaterialDescriptor().withDate(NOW))
				.build();
		final CandidatesQuery query = CandidatesQuery.fromCandidate(cand, false);
		assertThat(query.getMaterialDescriptorQuery().getProductId()).isEqualTo(PRODUCT_ID);
		assertThat(query.getMaterialDescriptorQuery().getStorageAttributesKey()).isEqualTo(STORAGE_ATTRIBUTES_KEY);
		assertThat(query.getMaterialDescriptorQuery().getWarehouseId()).isEqualTo(WAREHOUSE_ID);

		assertThat(query.getType()).isEqualTo(CandidateType.STOCK);
		assertThat(query.getParentId()).isEqualTo(CandidateId.UNSPECIFIED);
	}