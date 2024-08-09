	@Test
	public void createAttributesKeyFromASIStorageAttributes()
	{
		final I_M_Attribute attr1 = createStorageRelevantAttribute("test1");
		final AttributeListValue attributeValue1 = attributesTestHelper.createM_AttributeValue(attr1, "testValue1");

		final I_M_Attribute attr2 = createStorageRelevantAttribute("test2");
		final AttributeListValue attributeValue2 = attributesTestHelper.createM_AttributeValue(attr2, "testValue2");

		final I_M_AttributeSetInstance asi = newInstance(I_M_AttributeSetInstance.class);
		saveRecord(asi);
		final AttributeSetInstanceId asiId = AttributeSetInstanceId.ofRepoId(asi.getM_AttributeSetInstance_ID());

		attributeSetInstanceBL.getCreateAttributeInstance(asiId, attributeValue1);
		attributeSetInstanceBL.getCreateAttributeInstance(asiId, attributeValue2);

		// invoke the method under test
		final Optional<AttributesKey> result = AttributesKeys.createAttributesKeyFromASIStorageAttributes(asiId);
		assertThat(result).isPresent();

		final AttributesKey expectedResult = AttributesKey.ofAttributeValueIds(attributeValue1.getId(), attributeValue2.getId());
		assertThat(result).contains(expectedResult);
	}