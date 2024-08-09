public static Optional<AttributesKey> createAttributesKeyFromASIStorageAttributes(@NonNull final AttributeSetInstanceId attributeSetInstanceId)
	{
		if (attributeSetInstanceId.isNone())
		{
			return Optional.empty();
		}

		return createAttributesKeyFromASI(attributeSetInstanceId, asiService()::isStorageRelevant);
	}