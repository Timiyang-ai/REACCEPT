@Test
	@SkipBaseSetup
	@Verifies(value = "should set the referer as the denied page url if no redirect url is specified", method = "doStartTag()")
	public void doStartTag_shouldSetTheRefererAsTheDeniedPageUrlIfNoRedirectUrlIsSpecified() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("org/openmrs/web/taglib/include/RequireTagTest.xml");
		Context.authenticate("whirleygiguser", "test");
		
		RequireTag tag = new RequireTag();
		MockPageContext pageContext = new MockPageContext();
		final String referer = "/denied.htm";
		((MockHttpServletRequest) pageContext.getRequest()).addHeader("Referer", referer);
		tag.setPageContext(pageContext);
		tag.setAllPrivileges("Manage WhirleyGigs,Manage Thingamajigs");
		tag.setRedirect("");
		
		Assert.assertEquals(Tag.SKIP_PAGE, tag.doStartTag());
		Assert.assertEquals(true, pageContext.getAttribute(WebConstants.INSUFFICIENT_PRIVILEGES, PageContext.SESSION_SCOPE));
		Assert.assertNotNull(pageContext.getAttribute(WebConstants.REQUIRED_PRIVILEGES, PageContext.SESSION_SCOPE));
		Assert.assertEquals(referer, pageContext.getAttribute(WebConstants.DENIED_PAGE, PageContext.SESSION_SCOPE)
		        .toString());
		
		Context.logout();
	}