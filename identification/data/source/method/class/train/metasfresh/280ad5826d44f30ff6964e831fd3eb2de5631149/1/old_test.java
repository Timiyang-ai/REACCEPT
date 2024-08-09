@Test
	public void findPlant_WarehouseWithPlant()
	{
		final I_S_Resource plant = createPlant("Plant");
		final I_M_Warehouse warehouse = createWarehouse("Warehouse", plant);
		final int adOrgId = -1; // N/A
		final int productId = -1; // N/A
		final int attributeSetInstanceId = AttributeConstants.M_AttributeSetInstance_ID_None;

		final I_S_Resource plantActual = productPlanningDAO.findPlant(
				context.getCtx(),
				adOrgId,
				warehouse,
				productId,
				attributeSetInstanceId);

		Assert.assertNotNull("plant shall be found", plantActual);
		Assert.assertEquals("invalid plant", plant.getS_Resource_ID(), plantActual.getS_Resource_ID());
	}