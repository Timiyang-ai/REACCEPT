	@Test
	void setQty_isUOMForTUs()
	{
		final I_C_UOM coliUomRecord = newInstance(I_C_UOM.class);
		coliUomRecord.setX12DE355(IUOMDAO.X12DE355_COLI);
		saveRecord(coliUomRecord);

		final I_C_UOM eachUomRecord = newInstance(I_C_UOM.class);
		eachUomRecord.setX12DE355(IUOMDAO.X12DE355_Each);
		saveRecord(eachUomRecord);

		final I_M_Product productRecord = newInstance(I_M_Product.class);
		productRecord.setC_UOM_ID(eachUomRecord.getC_UOM_ID());
		saveRecord(productRecord);

		final I_EDI_DesadvLine_Pack desadvLinePackRecord = newInstance(I_EDI_DesadvLine_Pack.class);
		//desadvLinePackRecord.setQtyCU(new BigDecimal("9"));
		desadvLinePackRecord.setC_UOM_ID(coliUomRecord.getC_UOM_ID());
		desadvLinePackRecord.setQtyItemCapacity(new BigDecimal("9"));
		desadvLinePackRecord.setQtyCUsPerLU(new BigDecimal("99"));
		saveRecord(desadvLinePackRecord);

		// invoke the method under test
		desadvBL.setQty(
				ProductId.ofRepoId(productRecord.getM_Product_ID()),
				desadvLinePackRecord,
				Quantity.of("99999", eachUomRecord) /*qtyCUInStockUom*/,
				Quantity.of("20.5", eachUomRecord) /*qtyCUsPerLUInStockUom*/);

		assertThat(desadvLinePackRecord)
				.extracting(
						"QtyCUsPerLU",
						"QtyCU")
				.containsExactly(
						new BigDecimal("3"),
						new BigDecimal("1"));
	}