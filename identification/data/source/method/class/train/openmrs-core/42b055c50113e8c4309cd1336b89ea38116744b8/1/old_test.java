@Test
	public void toString_shouldNotFailIfUuidIsNull() throws Exception {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		//when
		o.setUuid(null);
		
		//then
		o.toString();
	}