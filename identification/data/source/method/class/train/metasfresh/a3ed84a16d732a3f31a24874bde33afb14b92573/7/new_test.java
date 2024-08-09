	@Test
	void createOrUpdateBPartner_create()
	{
		final int initialBPartnerRecordCount = POJOLookupMap.get().getRecords(I_C_BPartner.class).size();
		final int initialUserRecordCount = POJOLookupMap.get().getRecords(I_AD_User.class).size();
		final int initialBPartnerLocationRecordCount = POJOLookupMap.get().getRecords(I_C_BPartner_Location.class).size();
		final int initialLocationRecordCount = POJOLookupMap.get().getRecords(I_C_Location.class).size();

		createCountryRecord("CH");
		createCountryRecord("DE");

		final String externalId = C_BPARTNER_EXTERNAL_ID + "_2";
		final JsonRequestComposite bpartnerComposite = MockedDataUtil
				.createMockBPartner("ext-" + externalId);

		assertThat(bpartnerComposite.getContactsNotNull().getRequestItems()).hasSize(2); // guard
		assertThat(bpartnerComposite.getLocationsNotNull().getRequestItems()).hasSize(2);// guard

		final JsonRequestBPartner bpartner = bpartnerComposite.getBpartner()
				.toBuilder()
				.group(BP_GROUP_RECORD_NAME)
				.build();

		final JsonRequestBPartnerUpsertItem requestItem = JsonRequestBPartnerUpsertItem.builder()
				.bpartnerIdentifier("ext-" + externalId)
				.bpartnerComposite(bpartnerComposite.toBuilder()
						.bpartner(bpartner)
						.build())
				.build();

		final JsonRequestBPartnerUpsert bpartnerUpsertRequest = JsonRequestBPartnerUpsert.builder()
				.syncAdvise(SyncAdvise.CREATE_OR_MERGE)
				.requestItem(requestItem)
				.build();

		SystemTime.setTimeSource(() -> 1561134560); // Fri, 21 Jun 2019 16:29:20 GMT

		// JSONObjectMapper.forClass(JsonRequestBPartnerUpsert.class).writeValueAsString(bpartnerUpsertRequest);
		// invoke the method under test
		final ResponseEntity<JsonResponseUpsert> result = bpartnerRestController.createOrUpdateBPartner(bpartnerUpsertRequest);

		final MetasfreshId metasfreshId = assertUpsertResultOK(result, "ext-" + externalId);
		BPartnerId bpartnerId = BPartnerId.ofRepoId(metasfreshId.getValue());

		final BPartnerComposite persistedResult = bpartnerCompositeRepository.getById(bpartnerId);
		expect(persistedResult).toMatchSnapshot();

		assertThat(POJOLookupMap.get().getRecords(I_C_BPartner.class)).hasSize(initialBPartnerRecordCount + 1);
		assertThat(POJOLookupMap.get().getRecords(I_AD_User.class)).hasSize(initialUserRecordCount + 2);
		assertThat(POJOLookupMap.get().getRecords(I_C_BPartner_Location.class)).hasSize(initialBPartnerLocationRecordCount + 2);
		assertThat(POJOLookupMap.get().getRecords(I_C_Location.class)).hasSize(initialLocationRecordCount + 2);
	}