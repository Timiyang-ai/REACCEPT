public boolean setRecordField(
			@NonNull final DataEntryFieldId dataEntryFieldId,
			@NonNull final UserId updatedBy,
			@Nullable final Object value)
	{
		assertReadWrite();

		final DataEntryRecordField<?> previousFieldVersion = fields.get(dataEntryFieldId);

		final Object previousValue = previousFieldVersion == null ? null : previousFieldVersion.getValue();
		final boolean valueChanged = !Objects.equals(previousValue, value);

		if (!valueChanged)
		{
			return false;
		}

		final ZonedDateTime updated = ZonedDateTime.now();
		final DataEntryCreatedUpdatedInfo createdUpdatedInfo;
		if (previousFieldVersion == null)
		{
			createdUpdatedInfo = DataEntryCreatedUpdatedInfo.createNew(updatedBy, updated);
		}
		else
		{
			createdUpdatedInfo = previousFieldVersion.getCreatedUpdatedInfo().updated(updatedBy, updated);
		}

		final DataEntryRecordField<?> dataEntryRecordField = value != null
				? DataEntryRecordField.createDataEntryRecordField(dataEntryFieldId, createdUpdatedInfo, value)
				: DataEntryRecordFieldString.of(dataEntryFieldId, createdUpdatedInfo, null);

		fields.put(dataEntryFieldId, dataEntryRecordField);
		return true;
	}