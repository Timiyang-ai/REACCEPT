@Test
	public void testCreateAndAddEvents()
	{
		final I_AD_User user1 = newInstance(I_AD_User.class);
		user1.setLogin("user");
		user1.setName("we-just-need-some-record-as-a-reference");
		save(user1);

		final I_AD_User user2 = newInstance(I_AD_User.class);
		user2.setLogin("user2");
		user1.setName("we-just-need-some-record-as-a-reference");
		save(user2);

		final I_M_HU luHu11 = saveFluend(newInstance(I_M_HU.class));
		final I_M_HU vhu11 = saveFluend(newInstance(I_M_HU.class));
		vhu11.setHUStatus(X_M_HU.HUSTATUS_Active);

		final I_M_Product prod11 = saveFluend(newInstance(I_M_Product.class));
		final BigDecimal qty11 = BigDecimal.valueOf(11);

		final I_M_HU luHu12 = saveFluend(newInstance(I_M_HU.class));
		final I_M_HU vhu12 = saveFluend(newInstance(I_M_HU.class));
		vhu12.setHUStatus(X_M_HU.HUSTATUS_Active);

		final I_M_Product prod12 = saveFluend(newInstance(I_M_Product.class));
		final BigDecimal qty12 = BigDecimal.valueOf(12);

		final I_M_HU luHu21 = saveFluend(newInstance(I_M_HU.class));
		final I_M_HU vhu21 = saveFluend(newInstance(I_M_HU.class));
		vhu21.setHUStatus(X_M_HU.HUSTATUS_Active);

		final I_M_Product prod21 = saveFluend(newInstance(I_M_Product.class));
		final BigDecimal qty21 = BigDecimal.valueOf(21);

		final I_M_HU luHu22 = saveFluend(newInstance(I_M_HU.class));
		final I_M_HU vhu22 = saveFluend(newInstance(I_M_HU.class));
		vhu22.setHUStatus(X_M_HU.HUSTATUS_Active);

		final I_M_Product prod22 = saveFluend(newInstance(I_M_Product.class));
		final BigDecimal qty22 = BigDecimal.valueOf(22);

		{
			final TableRecordReference ref1 = TableRecordReference.of(user1);

			final I_M_HU_Assignment huAssignment11 = newInstance(I_M_HU_Assignment.class);
			huAssignment11.setM_HU_ID(luHu11.getM_HU_ID());
			huAssignment11.setVHU(vhu11);
			huAssignment11.setAD_Table_ID(ref1.getAD_Table_ID());
			huAssignment11.setRecord_ID(ref1.getRecord_ID());
			save(huAssignment11);

			final I_M_HU_Assignment huAssignment12 = newInstance(I_M_HU_Assignment.class);
			huAssignment12.setM_HU_ID(luHu12.getM_HU_ID());
			huAssignment12.setVHU(vhu12);
			huAssignment12.setAD_Table_ID(ref1.getAD_Table_ID());
			huAssignment12.setRecord_ID(ref1.getRecord_ID());
			save(huAssignment12);

			final TableRecordReference ref2 = TableRecordReference.of(user2);

			final I_M_HU_Assignment huAssignment21 = newInstance(I_M_HU_Assignment.class);
			huAssignment21.setM_HU_ID(luHu21.getM_HU_ID());
			huAssignment21.setVHU(vhu21);
			huAssignment21.setAD_Table_ID(ref2.getAD_Table_ID());
			huAssignment21.setRecord_ID(ref2.getRecord_ID());
			save(huAssignment21);

			final I_M_HU_Assignment huAssignment22 = newInstance(I_M_HU_Assignment.class);
			huAssignment22.setM_HU_ID(luHu22.getM_HU_ID());
			huAssignment22.setVHU(vhu22);
			huAssignment22.setAD_Table_ID(ref2.getAD_Table_ID());
			huAssignment22.setRecord_ID(ref2.getRecord_ID());
			save(huAssignment22);

			// create a 5th assignment that references user2 but has the same HU-ID *and* updated time! as huAssignment22
			// @formatter:off
			SystemTime.setTimeSource(new TimeSource()
			{
				@Override public long millis() { return huAssignment22.getUpdated().getTime(); }
			});
			// @formatter:on
			final I_M_HU_Assignment huAssignment22double = newInstance(I_M_HU_Assignment.class);
			huAssignment22double.setM_HU_ID(luHu22.getM_HU_ID());
			huAssignment22double.setVHU(vhu22);
			huAssignment22double.setAD_Table_ID(ref1.getAD_Table_ID());
			huAssignment22double.setRecord_ID(ref1.getRecord_ID());
			save(huAssignment22double);

			SystemTime.resetTimeSource();

			// set up the mocked huAccessService
			// @formatter:off
			new Expectations()
			{{
				huAccessService.retrieveHuAssignments(user1);
				result = ImmutableList.of(huAssignment11, huAssignment12);
				huAccessService.retrieveHuAssignments(user2);
				result = ImmutableList.of(huAssignment21, huAssignment22, huAssignment22double);
				
				// the LU HUs are already top level HUs themselves, so the method shall return their IDs
				huAccessService.retrieveTopLevelHuId(luHu11); result = luHu11.getM_HU_ID();
				huAccessService.retrieveTopLevelHuId(luHu12); result = luHu12.getM_HU_ID();
				huAccessService.retrieveTopLevelHuId(luHu21); result = luHu21.getM_HU_ID();
				huAccessService.retrieveTopLevelHuId(luHu22); result = luHu22.getM_HU_ID();
				
				huAccessService.retrieveProductAndQty(vhu11); result = Optional.of(ImmutablePair.of(prod11, qty11));
				huAccessService.retrieveProductAndQty(vhu12); result = Optional.of(ImmutablePair.of(prod12, qty12));
				huAccessService.retrieveProductAndQty(vhu21); result = Optional.of(ImmutablePair.of(prod21, qty21));
				huAccessService.retrieveProductAndQty(vhu22); result = Optional.of(ImmutablePair.of(prod22, qty22));
			}};
			// @formatter:on
		}

		final HUTraceEventBuilder builder = HUTraceEvent.builder()
				.inOutId(12).type(HUTraceType.MATERIAL_SHIPMENT); // note: inOutId and type don't really matter for this test

		huTraceEventsService.createAndAddEvents(builder, ImmutableList.of(user1, user2));

		final List<I_M_HU_Trace> allDBRecords = Services.get(IQueryBL.class).createQueryBuilder(I_M_HU_Trace.class)
				.create().list();

		assertThat(allDBRecords).hasSize(4); // there shall be no record for the 5th assignment.
		allDBRecords.sort(Comparator.comparing(I_M_HU_Trace::getM_HU_ID));

		assertThat(allDBRecords.get(0).getM_HU_ID()).isEqualTo(luHu11.getM_HU_ID());
		assertThat(allDBRecords.get(1).getM_HU_ID()).isEqualTo(luHu12.getM_HU_ID());
		assertThat(allDBRecords.get(2).getM_HU_ID()).isEqualTo(luHu21.getM_HU_ID());
		assertThat(allDBRecords.get(3).getM_HU_ID()).isEqualTo(luHu22.getM_HU_ID());
	}