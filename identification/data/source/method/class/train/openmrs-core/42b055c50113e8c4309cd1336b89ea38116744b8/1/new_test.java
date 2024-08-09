@Test
	public void toString_shouldIncludeHashCodeIfUuidIsNull() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		o.setUuid(null);
		
		//when
		//then
		assertEquals("BaseOpenmrsObjectMock{hashCode=" + Integer.toHexString(o.hashCode()) + "}", o.toString());
	}