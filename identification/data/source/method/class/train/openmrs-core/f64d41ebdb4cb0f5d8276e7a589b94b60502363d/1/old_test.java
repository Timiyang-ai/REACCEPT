@Test
	@SkipBaseSetup
	@Verifies(value = "should set the right session attributes if the authenticated user misses some privileges", method = "doStartTag()")
	public void doStartTag_shouldSetTheRightSessionAttributesIfTheAuthenticatedUserMissesSomePrivileges() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("org/openmrs/web/taglib/include/RequireTagTest.xml");
		Context.authenticate("whirleygiguser", "test");
		
		RequireTag tag = new RequireTag();
		MockPageContext pageContext = new MockPageContext();
		final String referer = "/denied.htm";
		((MockHttpServletRequest) pageContext.getRequest()).addHeader("Referer", referer);
		tag.setPageContext(pageContext);
		tag.setAllPrivileges("Manage WhirleyGigs,Manage Thingamajigs");
		
		Assert.assertEquals(Tag.SKIP_PAGE, tag.doStartTag());
		Object missingPrivilegesObj = pageContext.getAttribute(WebConstants.FOUND_MISSING_PRIVILEGES,
		    PageContext.SESSION_SCOPE);
		Assert.assertNotNull(missingPrivilegesObj);
		Assert.assertTrue(Boolean.valueOf(missingPrivilegesObj.toString()));
		
		Object errorMsg = pageContext.getAttribute(WebConstants.OPENMRS_ERROR_ATTR, PageContext.SESSION_SCOPE);
		Assert.assertNotNull(errorMsg);
		Assert.assertTrue(errorMsg.toString().indexOf("general.authentication.unableToViewPage") > -1);
		Assert.assertTrue(errorMsg.toString().indexOf("general.authentication.accountHasNoPrivilege") > -1);
		
		Assert.assertEquals(referer, pageContext.getAttribute(WebConstants.DENIED_PAGE, PageContext.SESSION_SCOPE)
		        .toString());
		
		Context.logout();
	}