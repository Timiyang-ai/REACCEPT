	@Test
	void createOrUpdateContact_create()
	{
		final JsonRequestContactUpsertItem jsonContact = MockedDataUtil.createMockContact("newContact-");

		SystemTime.setTimeSource(() -> 1561134560); // Fri, 21 Jun 2019 16:29:20 GMT

		// invoke the method under test
		final ResponseEntity<JsonResponseUpsert> result = bpartnerRestController.createOrUpdateContact(
				"gln-" + C_BPARTNER_LOCATION_GLN,
				JsonRequestContactUpsert.builder().requestItem(jsonContact).build());

		assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);

		final JsonResponseUpsert response = result.getBody();
		assertThat(response.getResponseItems()).hasSize(1);
		final JsonResponseUpsertItem responseItem = response.getResponseItems().get(0);
		assertThat(responseItem.getIdentifier()).isEqualTo(jsonContact.getContactIdentifier());

		final MetasfreshId metasfreshId = responseItem.getMetasfreshId();

		final BPartnerContactQuery bpartnerContactQuery = BPartnerContactQuery.builder().userId(UserId.ofRepoId(metasfreshId.getValue())).build();
		final Optional<BPartnerCompositeAndContactId> optContactIdAndBPartner = bpartnerCompositeRepository.getByContact(bpartnerContactQuery);
		assertThat(optContactIdAndBPartner).isPresent();

		final BPartnerContactId resultContactId = optContactIdAndBPartner.get().getBpartnerContactId();
		assertThat(resultContactId.getRepoId()).isEqualTo(metasfreshId.getValue());

		final BPartnerComposite persistedResult = optContactIdAndBPartner.get().getBpartnerComposite();
		expect(persistedResult).toMatchSnapshot();
	}