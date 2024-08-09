@Test
	public void test_CUToExistingTU_create_mixed_TU_completeCU()
	{
		final BigDecimal four = new BigDecimal("4");
		final IHUDocumentFactoryService huDocumentFactoryService = Services.get(IHUDocumentFactoryService.class);

		final I_M_HU cu1 = mkRealCUWithTUToSplit("5");
		final I_M_HU tuWithMixedCUs = handlingUnitsDAO.retrieveParent(cu1);
		final I_M_ReceiptSchedule rs1 = create_receiptSchedule_for_realCUWithTU(cu1, "5");
		final TableRecordReference rs1TableRef = TableRecordReference.of(rs1);

		final List<IHUDocument> rs1HuDocument = huDocumentFactoryService.createHUDocuments(data.helper.getHUContext().getCtx(), rs1TableRef.getTableName(), rs1TableRef.getRecord_ID());
		assertThat(rs1HuDocument.size(), is(1));
		assertThat(rs1HuDocument.get(0).getAssignedHandlingUnits().stream().anyMatch(hu -> hu.getM_HU_ID() == cu1.getM_HU_ID() || hu.getM_HU_ID() == tuWithMixedCUs.getM_HU_ID()), is(true));

		final HUProducerDestination producer = HUProducerDestination.ofVirtualPI();
		data.helper.load(producer, data.helper.pSalad, four, data.helper.uomKg);

		final I_M_HU cu2 = producer.getCreatedHUs().get(0);
		final I_M_ReceiptSchedule rs2 = create_receiptSchedule_for_realCUWithTU(cu2, "4");
		final TableRecordReference rs2TableRef = TableRecordReference.of(rs2);

		final List<IHUDocument> rs2HuDocument = huDocumentFactoryService.createHUDocuments(data.helper.getHUContext().getCtx(), rs2TableRef.getTableName(), rs2TableRef.getRecord_ID());
		assertThat(rs2HuDocument.size(), is(1));
		assertThat(rs2HuDocument.get(0).getAssignedHandlingUnits().stream().anyMatch(hu -> hu.getM_HU_ID() == cu2.getM_HU_ID()), is(true));

		final IHUReceiptScheduleDAO huReceiptScheduleDAO = Services.get(IHUReceiptScheduleDAO.class);

		HUTransferService.get(data.helper.getHUContext())
				.withReferencedObjects(ImmutableList.of(
						rs1TableRef,
						TableRecordReference.of(rs2)))
				.cuToExistingTU(cu2, four, tuWithMixedCUs);

		// data.helper.commitAndDumpHU(tuWithMixedCUs);
		final Node tuWithMixedCUsXML = HUXmlConverter.toXml(tuWithMixedCUs);
		assertThat(tuWithMixedCUsXML, hasXPath("string(HU-TU_IFCO/Storage[@M_Product_Value='Tomato' and @C_UOM_Name='Kg']/@Qty)", is("5.000")));
		assertThat(tuWithMixedCUsXML, hasXPath("string(HU-TU_IFCO/Storage[@M_Product_Value='Salad' and @C_UOM_Name='Kg']/@Qty)", is("4.000")));

		assertThat(tuWithMixedCUsXML, hasXPath("count(HU-TU_IFCO/Item[@ItemType='MI']/HU-VirtualPI[@M_HU_ID=" + cu1.getM_HU_ID() + "])", is("1")));
		assertThat(tuWithMixedCUsXML, hasXPath("string(HU-TU_IFCO/Item[@ItemType='MI']/HU-VirtualPI[@M_HU_ID=" + cu1.getM_HU_ID() + "]/Storage[@M_Product_Value='Tomato' and @C_UOM_Name='Kg']/@Qty)", is("5.000")));

		assertThat(tuWithMixedCUsXML, hasXPath("count(HU-TU_IFCO/Item[@ItemType='MI']/HU-VirtualPI[@M_HU_ID=" + cu2.getM_HU_ID() + "])", is("1")));
		assertThat(tuWithMixedCUsXML, hasXPath("string(HU-TU_IFCO/Item[@ItemType='MI']/HU-VirtualPI[@M_HU_ID=" + cu2.getM_HU_ID() + "]/Storage[@M_Product_Value='Salad' and @C_UOM_Name='Kg']/@Qty)", is("4.000")));
		{
			final I_M_ReceiptSchedule receiptScheduleForCU1 = huReceiptScheduleDAO.retrieveReceiptScheduleForVHU(cu1);
			assertThat(receiptScheduleForCU1, notNullValue());
			assertThat(receiptScheduleForCU1.getM_ReceiptSchedule_ID(), is(rs1.getM_ReceiptSchedule_ID()));

			final List<I_M_ReceiptSchedule_Alloc> rsas1 = huReceiptScheduleDAO.retrieveHandlingUnitAllocations(receiptScheduleForCU1, data.helper.getHUContext().getTrxName());
			final List<I_M_ReceiptSchedule_Alloc> rsas1ForCu1 = rsas1.stream().filter(rsa -> rsa.getM_TU_HU_ID() == tuWithMixedCUs.getM_HU_ID() && rsa.getVHU_ID() == cu1.getM_HU_ID()).collect(Collectors.toList());
			assertThat(rsas1ForCu1.size(), is(1));
			assertThat(rsas1ForCu1.get(0).getHU_QtyAllocated(), comparesEqualTo(new BigDecimal("5")));
		}
		{
			final I_M_ReceiptSchedule receiptScheduleForCU2 = huReceiptScheduleDAO.retrieveReceiptScheduleForVHU(cu2);
			assertThat(receiptScheduleForCU2, notNullValue());
			assertThat(receiptScheduleForCU2.getM_ReceiptSchedule_ID(), is(rs2.getM_ReceiptSchedule_ID()));

			final List<I_M_ReceiptSchedule_Alloc> rsas2 = huReceiptScheduleDAO.retrieveHandlingUnitAllocations(receiptScheduleForCU2, data.helper.getHUContext().getTrxName());
			final List<I_M_ReceiptSchedule_Alloc> rsas2ForCu2 = rsas2.stream().filter(rsa -> rsa.getM_TU_HU_ID() == tuWithMixedCUs.getM_HU_ID() && rsa.getVHU_ID() == cu2.getM_HU_ID()).collect(Collectors.toList());
			assertThat(rsas2ForCu2.size(), is(1));
			assertThat(rsas2ForCu2.get(0).getHU_QtyAllocated(), comparesEqualTo(four));
		}

	}