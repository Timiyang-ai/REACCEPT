public boolean setRecordField(
			@NonNull final DataEntryFieldId dataEntryFieldId,
			@NonNull final UserId updatedBy,
			@Nullable final Object value)
	{
		final DataEntryRecordField<?> previousFieldVersion = fields.remove(dataEntryFieldId);

		final Object previousValue = previousFieldVersion == null ? null : previousFieldVersion.getValue();
		final boolean valueChanged = !Objects.equal(previousValue, value);

		if (value == null)
		{
			return valueChanged;
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
		return valueChanged;
	}