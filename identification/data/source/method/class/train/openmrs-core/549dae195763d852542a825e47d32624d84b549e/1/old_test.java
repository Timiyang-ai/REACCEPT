	@Test
	public void toString_shouldIncludeUuidIfNotNull() {
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		assertEquals("BaseOpenmrsObjectTest.BaseOpenmrsObjectMock[hashCode=" + Integer.toHexString(o.hashCode()) + ",uuid="
		        + o.getUuid() + "]", o.toString());
	}