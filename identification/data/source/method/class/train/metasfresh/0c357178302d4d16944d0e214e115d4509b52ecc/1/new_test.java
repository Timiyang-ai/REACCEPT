	@Test
	public void test_createProcessInfoParameter_Timestamp()
	{
		final Timestamp date = SystemTime.asDayTimestamp();
		final I_AD_PInstance_Para adPInstancePara = InterfaceWrapperHelper.newInstance(I_AD_PInstance_Para.class, context);
		adPInstancePara.setP_Date(date);

		final ProcessInfoParameter para = dao.createProcessInfoParameter(adPInstancePara);
		Assert.assertEquals(date, para.getParameterAsTimestamp());
	}