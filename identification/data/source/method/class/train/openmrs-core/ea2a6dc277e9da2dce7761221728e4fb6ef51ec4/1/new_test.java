	@Test
	public void equals_shouldReturnFalseIfGivenObjHasNullUuid() {
		//given
		BaseOpenmrsObject o = new BaseOpenmrsObjectMock();
		BaseOpenmrsObject obj = new BaseOpenmrsObjectMock();
		
		//when
		obj.setUuid(null);
		
		//then
		Assert.assertFalse(o.equals(obj));
	}