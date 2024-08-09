public static Optional<AttributesKey> createAttributesKeyFromASIStorageAttributes(final int attributeSetInstanceId)
	{
		return createAttributesKeyWithFilter(
				attributeSetInstanceId,
				ai -> ai.getM_Attribute().isStorageRelevant());
	}