	@Test
	void createOrUpdateLocation_create()
	{
		createCountryRecord("DE");
		final JsonRequestLocationUpsertItem jsonLocation = MockedDataUtil.createMockLocation("newLocation-", "DE");

		SystemTime.setTimeSource(() -> 1561134560); // Fri, 21 Jun 2019 16:29:20 GMT

		// invoke the method under test
		final ResponseEntity<JsonResponseUpsert> result = bpartnerRestController.createOrUpdateLocation(
				"ext-" + C_BPARTNER_EXTERNAL_ID,
				JsonRequestLocationUpsert.builder().requestItem(jsonLocation).build());

		assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);

		final JsonResponseUpsert response = result.getBody();
		assertThat(response.getResponseItems()).hasSize(1);
		final JsonResponseUpsertItem responseItem = response.getResponseItems().get(0);

		assertThat(responseItem.getIdentifier()).isEqualTo(jsonLocation.getLocationIdentifier());

		final MetasfreshId metasfreshId = responseItem.getMetasfreshId();

		final BPartnerQuery query = BPartnerQuery.builder().externalId(ExternalId.of(C_BPARTNER_EXTERNAL_ID)).build();
		final ImmutableList<BPartnerComposite> persistedPage = bpartnerCompositeRepository.getByQuery(query);

		assertThat(persistedPage).hasSize(1);

		final BPartnerComposite persistedResult = persistedPage.get(0);
		final Optional<BPartnerLocation> persistedLocation = persistedResult.extractLocation(BPartnerLocationId.ofRepoId(persistedResult.getBpartner().getId(), metasfreshId.getValue()));
		assertThat(persistedLocation).isPresent();

		assertThat(persistedLocation.get().getId().getRepoId()).isEqualTo(metasfreshId.getValue());

		expect(persistedResult).toMatchSnapshot();
	}