	public void test_toString() {
		// Test for method java.lang.String java.text.FieldPosition.toString()
		FieldPosition fpos = new FieldPosition(1);
		fpos.setBeginIndex(2);
		fpos.setEndIndex(3);
		// J2ObjC reflection-stripping change.
		String expected = "java.text.FieldPosition"
				+ "[field=1,attribute=null,beginIndex=2,endIndex=3]";
		assertTrue("ToString returned the wrong value:",
				ReflectionUtil.matchClassNamePrefix(fpos.toString(), expected));

		FieldPosition fpos2 = new FieldPosition(DateFormat.Field.ERA);
		fpos2.setBeginIndex(4);
		fpos2.setEndIndex(5);
		// J2ObjC reflection-stripping change.
		expected = "java.text.FieldPosition[field=-1,attribute="
				+ DateFormat.Field.ERA.toString() + ",beginIndex=4,endIndex=5]";
		assertTrue("ToString returned the wrong value:",
				ReflectionUtil.matchClassNamePrefix(fpos2.toString(), expected));
	}