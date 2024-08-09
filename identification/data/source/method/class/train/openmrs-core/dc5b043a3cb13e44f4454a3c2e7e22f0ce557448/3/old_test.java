	@Test
	public void voidedPersonAttributeExists_shouldReturnTrueIfAVoidedPersonAttributeExists() {
		Assert.assertTrue(helper.voidedPersonAttributeExists("Master thief"));
		Assert.assertTrue(helper.voidedPersonAttributeExists("Mushroom pie"));
		
		Assert.assertFalse(helper.voidedPersonAttributeExists("Unexpected attribute value"));
	}