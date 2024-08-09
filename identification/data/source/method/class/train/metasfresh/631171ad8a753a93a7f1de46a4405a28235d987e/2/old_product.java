public void setRecordField(
			@NonNull final DataEntryFieldId dataEntryFieldId,
			@NonNull final UserId updatedBy,
			@Nullable final Object value)
	{
		final DataEntryRecordField<?> previousFieldVersion = fields.remove(dataEntryFieldId);

		if (value == null)
		{
			return;
		}

		final ZonedDateTime updated = ZonedDateTime.now();
		CreatedUpdatedInfo createdUpdatedInfo;
		if (previousFieldVersion == null)
		{
			createdUpdatedInfo = CreatedUpdatedInfo.createNew(updatedBy, updated);
		}
		else
		{
			createdUpdatedInfo = previousFieldVersion.getCreatedUpdatedInfo().updated(updatedBy, updated);
		}

		final DataEntryRecordField<?> //
		dataEntryRecordField = DataEntryRecordField.createDataEntryRecordField(dataEntryFieldId, createdUpdatedInfo, value);

		fields.put(dataEntryFieldId, dataEntryRecordField);
	}