	@Test
	public void getName(){
		FieldName name = FieldName.create("y");

		DataField dataField = new DataField(name, OpType.CONTINUOUS, DataType.DOUBLE);

		TargetField targetField = new TargetField(dataField, null, null);

		assertEquals(name, targetField.getName());

		targetField.setName(FieldName.create("label"));

		assertNotEquals(name, targetField.getName());

		targetField.setName(null);

		assertEquals(name, targetField.getName());
	}