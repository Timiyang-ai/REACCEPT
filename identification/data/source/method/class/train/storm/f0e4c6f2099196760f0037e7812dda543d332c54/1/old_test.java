    @Test
    public void test_getUserName() {
        DefaultHttpCredentialsPlugin handler = new DefaultHttpCredentialsPlugin();
        handler.prepare(new HashMap());

        Assert.assertNull("returns null when request is null", handler.getUserName(null));

        Assert.assertNull("returns null when user principal is null", handler.getUserName(Mockito.mock(
            HttpServletRequest.class)));

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(mockRequest.getUserPrincipal()).thenReturn(new SingleUserPrincipal(""));
        Assert.assertNull("returns null when user is blank", handler.getUserName(mockRequest));

        String expName = "Alice";
        mockRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(mockRequest.getUserPrincipal()).thenReturn(new SingleUserPrincipal(expName));
        Assert.assertEquals("returns correct user from requests principal", expName, handler.getUserName(mockRequest));

        try {
            String doAsUserName = "Bob";
            mockRequest = Mockito.mock(HttpServletRequest.class);
            Mockito.when(mockRequest.getUserPrincipal()).thenReturn(new SingleUserPrincipal(expName));
            Mockito.when(mockRequest.getHeader("doAsUser")).thenReturn(doAsUserName);
            ReqContext context = handler.populateContext(ReqContext.context(), mockRequest);

            Assert.assertTrue(context.isImpersonating());
            Assert.assertEquals(expName, context.realPrincipal().getName());
            Assert.assertEquals(doAsUserName, context.principal().getName());
        } finally {
            ReqContext.reset();
        }
    }