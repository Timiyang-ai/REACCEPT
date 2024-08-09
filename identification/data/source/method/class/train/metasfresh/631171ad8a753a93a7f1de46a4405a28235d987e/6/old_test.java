	@Test
	public void setRecordField()
	{
		final DataEntrySubTabId dataEntrySubTabId = DataEntrySubTabId.ofRepoId(10);
		final DataEntryRecord dataEntryRecord = DataEntryRecord.builder()
				.dataEntrySubTabId(dataEntrySubTabId)
				.mainRecord(TableRecordReference.of(I_M_Product.Table_Name, 41))
				.build();

		final DataEntryFieldId fieldId1 = DataEntryFieldId.ofRepoId(1);
		final DataEntryFieldId fieldId2 = DataEntryFieldId.ofRepoId(2);
		final DataEntryFieldId fieldId3 = DataEntryFieldId.ofRepoId(3);
		final DataEntryFieldId fieldId4 = DataEntryFieldId.ofRepoId(4);
		final DataEntryFieldId fieldId5 = DataEntryFieldId.ofRepoId(5);
		final DataEntryFieldId fieldId6 = DataEntryFieldId.ofRepoId(6);

		// invoke method under test
		dataEntryRecord.setRecordField(fieldId1, UserId.ofRepoId(20), null);
		dataEntryRecord.setRecordField(fieldId2, UserId.ofRepoId(20), "text");
		dataEntryRecord.setRecordField(fieldId3, UserId.ofRepoId(20), "longText");
		dataEntryRecord.setRecordField(fieldId4, UserId.ofRepoId(20), true);
		dataEntryRecord.setRecordField(fieldId5, UserId.ofRepoId(20), new BigDecimal("15"));
		dataEntryRecord.setRecordField(fieldId6, UserId.ofRepoId(20), DATE);

		assertThat(dataEntryRecord.getFields()).isNotEmpty();
		assertThat(dataEntryRecord.getFields()).doesNotContainNull();

		final ImmutableMap<DataEntryFieldId, DataEntryRecordField<?>> resultMap = Maps.uniqueIndex(dataEntryRecord.getFields(), DataEntryRecordField::getDataEntryFieldId);

		assertThat(resultMap.get(fieldId1)).isNull();
		assertThat(resultMap.get(fieldId2).getValue()).isEqualTo("text");
		assertThat(resultMap.get(fieldId3).getValue()).isEqualTo("longText");
		assertThat(resultMap.get(fieldId4).getValue()).isEqualTo(true);
		assertThat(resultMap.get(fieldId5).getValue()).isEqualTo(new BigDecimal("15"));
		assertThat(resultMap.get(fieldId6).getValue()).isEqualTo(DATE);
	}