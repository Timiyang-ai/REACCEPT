	public void test_getField() {
		// Test for method int java.text.FieldPosition.getField()
		FieldPosition fpos = new FieldPosition(65);
		assertEquals("FieldPosition(65) should have caused getField to return 65",
				65, fpos.getField());
		FieldPosition fpos2 = new FieldPosition(DateFormat.Field.MINUTE);
		assertEquals("FieldPosition(DateFormat.Field.MINUTE) should have caused getField to return -1",
				-1, fpos2.getField());
	}