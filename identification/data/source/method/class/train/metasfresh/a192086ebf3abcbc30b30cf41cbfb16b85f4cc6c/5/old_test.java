	@BeforeEach
	public void init()
	{
		huTestHelper = new HUTestHelper();
		huContext = Services.get(IHUContextFactory.class).createMutableHUContext();

		final I_C_UOM_Conversion catchUOMConversionRecord = newInstance(I_C_UOM_Conversion.class);
		catchUOMConversionRecord.setM_Product_ID(huTestHelper.pTomatoProductId.getRepoId());
		catchUOMConversionRecord.setC_UOM_ID(huTestHelper.uomEachId.getRepoId());
		catchUOMConversionRecord.setC_UOM_To_ID(huTestHelper.uomKgId.getRepoId());
		catchUOMConversionRecord.setMultiplyRate(TEN);
		catchUOMConversionRecord.setDivideRate(ONE.divide(TEN));
		catchUOMConversionRecord.setIsCatchUOMForProduct(true);
		catchUOMConversionRecord.setIsActive(true);
		saveRecord(catchUOMConversionRecord);

		Services.registerService(IShipmentScheduleInvalidateBL.class, new ShipmentScheduleInvalidateBL(new PickingBOMService()));
		Services.get(IShipmentScheduleHandlerBL.class).registerHandler(OrderLineShipmentScheduleHandler.class);
		Services.registerService(IShipmentScheduleUpdater.class, ShipmentScheduleUpdater.newInstanceForUnitTesting());
	}