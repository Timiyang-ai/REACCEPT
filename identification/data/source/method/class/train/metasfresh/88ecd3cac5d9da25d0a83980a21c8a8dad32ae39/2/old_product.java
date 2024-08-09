public static Optional<AttributesKey> createAttributesKeyFromASIStorageAttributes(final AttributeSetInstanceId attributeSetInstanceId)
	{
		if (AttributeSetInstanceId.isRegular(attributeSetInstanceId))
		{
			return createAttributesKeyWithFilter(
					attributeSetInstanceId.getRepoId(),
					ai -> ai.getM_Attribute().isStorageRelevant());
		}
		else
		{
			return Optional.empty();
		}
	}