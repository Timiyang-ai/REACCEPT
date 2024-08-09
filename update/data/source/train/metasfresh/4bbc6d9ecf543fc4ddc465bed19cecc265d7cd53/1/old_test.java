@Test
	public void test_CUToExistingTU_create_mixed_TU_partialCU()
	{
		final I_M_HU cu1 = mkRealCUWithTUToSplit("2");
		final I_M_HU existingTU = handlingUnitsDAO.retrieveParent(cu1);

		final I_M_ReceiptSchedule rs1 = create_receiptSchedule_for_CU(cu1, "2");
		final TableRecordReference rs1TableRef = TableRecordReference.of(rs1);
		{ // verify that cuHU and rs1 are properly linked
			final List<IHUDocument> rs1HuDocument = huDocumentFactoryService.createHUDocuments(data.helper.getHUContext().getCtx(), rs1TableRef.getTableName(), rs1TableRef.getRecord_ID());
			assertThat(rs1HuDocument.size(), is(1));
			assertThat(rs1HuDocument.get(0).getAssignedHandlingUnits().stream().anyMatch(hu -> hu.getM_HU_ID() == cu1.getM_HU_ID() || hu.getM_HU_ID() == existingTU.getM_HU_ID()), is(true));
		}

		final HUProducerDestination producer = HUProducerDestination.ofVirtualPI();
		data.helper.load(producer, data.helper.pSalad, new BigDecimal("3"), data.helper.uomKg);
		final I_M_HU cu2 = producer.getCreatedHUs().get(0);
		final I_M_ReceiptSchedule rs2 = create_receiptSchedule_for_CU(cu2, "3");
		final TableRecordReference rs2TableRef = TableRecordReference.of(rs2);
		{ // verify that secondCU and rs2 are properly linked
			final List<IHUDocument> rs2HuDocument = huDocumentFactoryService.createHUDocuments(data.helper.getHUContext().getCtx(), rs2TableRef.getTableName(), rs2TableRef.getRecord_ID());
			assertThat(rs2HuDocument.size(), is(1));
			assertThat(rs2HuDocument.get(0).getAssignedHandlingUnits().stream().anyMatch(hu -> hu.getM_HU_ID() == cu2.getM_HU_ID() || hu.getM_HU_ID() == existingTU.getM_HU_ID()), is(true));
		}

		// invoke the method under test.
		HUTransformService.builderForHUcontext()
				.huContext(data.helper.getHUContext())
				.referencedObjects(ImmutableList.of(rs1TableRef, rs2TableRef))
				.build()
				.cuToExistingTU(cu2, Quantity.of(new BigDecimal("1.6"), data.helper.uomKg), existingTU);

		// secondCU is still there, with the remaining 1.4kg
		final Node secondCUXML = HUXmlConverter.toXml(cu2);
		assertThat(secondCUXML, hasXPath("string(HU-VirtualPI[@M_HU_ID=" + cu2.getM_HU_ID() + "]/@HUStatus)", is("P")));
		assertThat(secondCUXML, hasXPath("string(HU-VirtualPI[@M_HU_ID=" + cu2.getM_HU_ID() + "]/Storage[@M_Product_Value='Salad' and @C_UOM_Name='Kg']/@Qty)", is("1.400")));

		final Node existingLUXML = HUXmlConverter.toXml(existingTU);
		assertThat(existingLUXML, hasXPath("string(HU-TU_IFCO/Storage[@M_Product_Value='Tomato' and @C_UOM_Name='Kg']/@Qty)", is("2.000")));
		assertThat(existingLUXML, hasXPath("string(HU-TU_IFCO/Storage[@M_Product_Value='Salad' and @C_UOM_Name='Kg']/@Qty)", is("1.600")));
		assertThat(existingLUXML, hasXPath("string(HU-TU_IFCO/Item[@ItemType='MI']/Storage[@M_Product_Value='Tomato' and @C_UOM_Name='Kg']/@Qty)", is("2.000")));
		assertThat(existingLUXML, hasXPath("string(HU-TU_IFCO/Item[@ItemType='MI']/Storage[@M_Product_Value='Salad' and @C_UOM_Name='Kg']/@Qty)", is("1.600")));
		assertThat(existingLUXML, hasXPath("string(HU-TU_IFCO/Item[@ItemType='MI']/HU-VirtualPI[@M_HU_ID=" + cu1.getM_HU_ID() + "]/Storage[@M_Product_Value='Tomato' and @C_UOM_Name='Kg']/@Qty)", is("2.000")));
		assertThat(existingLUXML, hasXPath("string(HU-TU_IFCO/Item[@ItemType='MI']/HU-VirtualPI/Storage[@M_Product_Value='Salad' and @C_UOM_Name='Kg']/@Qty)", is("1.600")));

		// verify that the receipt M_ReceiptSchedule_Allocs are also OK
		final IHUReceiptScheduleDAO huReceiptScheduleDAO = Services.get(IHUReceiptScheduleDAO.class);
		{
			// verify cu1 that was effectively unchanged
			final I_M_ReceiptSchedule receiptScheduleForCU1 = huReceiptScheduleDAO.retrieveReceiptScheduleForVHU(cu1);
			assertThat(receiptScheduleForCU1, notNullValue());
			assertThat(receiptScheduleForCU1.getM_ReceiptSchedule_ID(), is(rs1.getM_ReceiptSchedule_ID()));

			final List<I_M_ReceiptSchedule_Alloc> rsas1 = huReceiptScheduleDAO.retrieveHandlingUnitAllocations(receiptScheduleForCU1, data.helper.getHUContext().getTrxName());
			final List<I_M_ReceiptSchedule_Alloc> rsas1ForCu1 = rsas1.stream().filter(rsa -> rsa.getM_TU_HU_ID() == existingTU.getM_HU_ID() && rsa.getVHU_ID() == cu1.getM_HU_ID()).collect(Collectors.toList());
			assertThat(rsas1ForCu1.size(), is(1));
			assertThat(rsas1ForCu1.get(0).getHU_QtyAllocated(), comparesEqualTo(new BigDecimal("2")));
		}
		{
			// // verify c2 which got 1.6 kg of salad chopped off
			final I_M_ReceiptSchedule receiptScheduleForCU2 = huReceiptScheduleDAO.retrieveReceiptScheduleForVHU(cu2);
			assertThat(receiptScheduleForCU2, notNullValue());
			assertThat(receiptScheduleForCU2.getM_ReceiptSchedule_ID(), is(rs2.getM_ReceiptSchedule_ID()));

			// TODO this doesn't work and i'm unsure why it doesen't work, but also how it should work..cu2 is split be HULoader, so there is alot going on with hu-transaction stuff.
			// final List<I_M_ReceiptSchedule_Alloc> rsas2 = huReceiptScheduleDAO.retrieveHandlingUnitAllocations(receiptScheduleForCU2, data.helper.getHUContext().getTrxName());
			// final List<I_M_ReceiptSchedule_Alloc> rsas2ForCu2 = rsas2.stream().filter(rsa -> rsa.getM_TU_HU_ID() == existingTU.getM_HU_ID() && rsa.getVHU_ID() == cu2.getM_HU_ID()).collect(Collectors.toList());
			// assertThat(rsas2ForCu2.size(), is(1));
			// assertThat(rsas2ForCu2.get(0).getHU_QtyAllocated(), comparesEqualTo(new BigDecimal("1.6")));
		}
		{
			final List<I_M_HU> siblingsOfCu1 = handlingUnitsDAO.retrieveIncludedHUs(existingTU).stream().filter(hu -> hu.getM_HU_ID() != cu1.getM_HU_ID()).collect(Collectors.toList());
			assertThat(siblingsOfCu1.size(), is(1));
			final I_M_HU newlySplitOffCU = siblingsOfCu1.get(0);
			// verify the new cu that was split off cu2 and is now below existingTU
			final I_M_ReceiptSchedule receiptScheduleForCU2_2 = huReceiptScheduleDAO.retrieveReceiptScheduleForVHU(newlySplitOffCU);
			assertThat(receiptScheduleForCU2_2, notNullValue());
			assertThat(receiptScheduleForCU2_2.getM_ReceiptSchedule_ID(), is(rs2.getM_ReceiptSchedule_ID()));

			final List<I_M_ReceiptSchedule_Alloc> rsas2 = huReceiptScheduleDAO.retrieveHandlingUnitAllocations(receiptScheduleForCU2_2, data.helper.getHUContext().getTrxName());
			final List<I_M_ReceiptSchedule_Alloc> rsas2ForCu2 = rsas2.stream().filter(rsa -> rsa.getM_TU_HU_ID() == existingTU.getM_HU_ID() && rsa.getVHU_ID() == newlySplitOffCU.getM_HU_ID()).collect(Collectors.toList());
			assertThat(rsas2ForCu2.size(), is(1));
			assertThat(rsas2ForCu2.get(0).getHU_QtyAllocated(), comparesEqualTo(new BigDecimal("1.6")));
		}
	}