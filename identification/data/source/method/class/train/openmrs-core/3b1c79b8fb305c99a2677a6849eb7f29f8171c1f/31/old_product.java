@Transactional(readOnly = true)
	public FieldType getFieldTypeByUuid(String uuid) throws APIException;