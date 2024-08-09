	public void test_getFieldAttribute() {
		// Test for method int java.text.FieldPosition.getFieldAttribute()
		FieldPosition fpos = new FieldPosition(DateFormat.Field.TIME_ZONE);
		assertTrue(
				"FieldPosition(DateFormat.Field.TIME_ZONE) should have caused getFieldAttribute to return DateFormat.Field.TIME_ZONE",
				fpos.getFieldAttribute() == DateFormat.Field.TIME_ZONE);

		FieldPosition fpos2 = new FieldPosition(DateFormat.TIMEZONE_FIELD);
		assertNull(
				"FieldPosition(DateFormat.TIMEZONE_FIELD) should have caused getFieldAttribute to return null",
				fpos2.getFieldAttribute());
	}