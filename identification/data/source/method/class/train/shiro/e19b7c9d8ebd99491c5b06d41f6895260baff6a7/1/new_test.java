@Test
    public void getRememberedPrincipals() {
        HttpServletRequest mockRequest = createMock(HttpServletRequest.class);
        HttpServletResponse mockResponse = createMock(HttpServletResponse.class);
        Map<String,Object> context = new HashMap<String,Object>();
        context.put(SubjectFactory.SERVLET_REQUEST, mockRequest);
        context.put(SubjectFactory.SERVLET_RESPONSE, mockResponse);

        expect(mockRequest.getAttribute(ShiroHttpServletRequest.IDENTITY_REMOVED_KEY)).andReturn(null);

        //The following base64 string was determined from the log output of the above 'onSuccessfulLogin' test.
        //This will have to change any time the PrincipalCollection implementation changes:
        final String userPCBlowfishBase64 = "UwP13UzjVUceLBNWh+sYM01JWOSbBOwc1ZLySIws0Idnkc" +
                "WeD/yWeH0eIycwHaI8MRKPyenBr77dBdt3S7KTKzzt47bdseNbEI7TbTKPY5VfnJLqGVglQr+O" +
                "mTgH1vpCQ/PAw3XnrQ4FWSXe9/KkfcAfteY5iw7qea1zZJq5jC4dOU3HLlhL7+BtlFMOrSzP2i" +
                "ijwEZGFoNASMTpLxTpiiTHhVmB9Hf4s7N2rTthK18+uTyJwC1KoK3Fw82Wxl7BZb5aFoc5BoJb" +
                "lWyZVHV3hEIIIS9/2smrjrCdu0NRC31c/+IelggTG3jTMA1wQ0oq2jTZSjctlcknV90jxNJfbf" +
                "/Uzk679TmgyrHJgRrQ+kqJ+94rafqFWEcaG82yT3LkQEjE6S8U6Yokx4VZgfR3+Nnhgfb36EfU" +
                "BXytFPop+38q1ssgLNxj3TPPOMj/QfGHVX6lM6loW8zA3VIEtDyqXN0LAQzqnbC8zqb1CJhXaJ" +
                "owmdO9LV7XzouBN+l/ER8I";

        Cookie[] cookies = new Cookie[]{
                new Cookie(WebRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME, userPCBlowfishBase64)
        };

        expect(mockRequest.getCookies()).andReturn(cookies);
        replay(mockRequest);

        WebRememberMeManager mgr = new WebRememberMeManager();
        PrincipalCollection collection = mgr.getRememberedPrincipals(context);

        verify(mockRequest);

        assertTrue(collection != null);
        //noinspection ConstantConditions
        assertTrue(collection.iterator().next().equals("user"));
    }