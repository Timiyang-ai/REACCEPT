	@Test
	public void isRequest() {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

		//Nothing set = bad request
		boolean isReq = BasicLTIUtil.isRequest(req);
		assertFalse(isReq);

		//Set both things = good request
		Mockito.when(req.getParameter(LTI_MESSAGE_TYPE)).thenReturn(LTI_MESSAGE_TYPE_BASICLTILAUNCHREQUEST);
		Mockito.when(req.getParameter(LTI_VERSION)).thenReturn(LTI_VERSION_1);
		isReq = BasicLTIUtil.isRequest(req);
		assertTrue(isReq);

		//Bad message type = bad request
		Mockito.when(req.getParameter(LTI_MESSAGE_TYPE)).thenReturn("foobar");
		isReq = BasicLTIUtil.isRequest(req);
		assertFalse(isReq);

		//Bad version = bad request
		Mockito.when(req.getParameter(LTI_MESSAGE_TYPE)).thenReturn(LTI_MESSAGE_TYPE_BASICLTILAUNCHREQUEST);
		Mockito.when(req.getParameter(LTI_VERSION)).thenReturn("taco");
		isReq = BasicLTIUtil.isRequest(req);
		assertFalse(isReq);
	}