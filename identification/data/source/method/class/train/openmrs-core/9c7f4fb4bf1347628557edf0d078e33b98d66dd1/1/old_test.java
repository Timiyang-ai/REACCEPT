	@Test
	public void getContextPath_shouldReturnEmptyStringWhenWebAppNameIsNull() {
		WebConstants.WEBAPP_NAME = null;
		Assert.assertEquals("", WebUtil.getContextPath());
	}