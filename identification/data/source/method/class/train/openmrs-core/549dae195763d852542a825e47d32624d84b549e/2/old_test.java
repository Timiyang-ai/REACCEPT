	@Test
	public void hashCode_shouldNotFailIfUuidIsNull() {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		
		//when
		o.setUuid(null);
		
		//then
		o.hashCode();
	}