@Test
	public void toString_shouldIncludeUuidIfNotNull() throws Exception {
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		assertEquals("BaseOpenmrsObjectMock{hashCode=" + Integer.toHexString(o.hashCode()) + ", uuid=" + o.getUuid() + "}",
		    o.toString());
	}