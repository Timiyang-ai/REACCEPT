public static Optional<AttributesKey> createAttributesKeyFromASIStorageAttributes(@NonNull final AttributeSetInstanceId attributeSetInstanceId)
	{
		if (attributeSetInstanceId.isNone())
		{
			return Optional.empty();
		}

		final IAttributeSetInstanceBL asiService = Services.get(IAttributeSetInstanceBL.class);
		return createAttributesKeyWithFilter(attributeSetInstanceId, asiService::isStorageRelevant);
	}