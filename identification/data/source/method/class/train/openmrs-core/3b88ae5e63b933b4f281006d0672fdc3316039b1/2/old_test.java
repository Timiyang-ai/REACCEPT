	@Test
	public void setSerializers_shouldNotResetSerializersListWhenCalledMultipleTimes() {
		SerializationServiceImpl ssi = new SerializationServiceImpl();
		Assert.assertEquals(0, ssi.getSerializers().size());
		
		ssi.setSerializers(Collections.singletonList(new MockSerializer1()));
		Assert.assertEquals(1, ssi.getSerializers().size());
		
		ssi.setSerializers(Collections.singletonList(new MockSerializer2()));
		Assert.assertEquals(2, ssi.getSerializers().size());
	}