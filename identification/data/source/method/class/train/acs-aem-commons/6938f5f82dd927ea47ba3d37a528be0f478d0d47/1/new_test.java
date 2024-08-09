@Test
    public void testDropCookies() {
        String[] cookieNames = {"dog-mammal", "cat-mammal"};
        CookieUtil.dropCookies(request, response, "/", cookieNames);
        assertTrue(true);

        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);

        verify(response, times(2)).addCookie(cookieCaptor.capture());

        for (Cookie cookie : cookieCaptor.getAllValues()) {
            assertEquals(0, cookie.getMaxAge());
            assertEquals("", cookie.getValue());
        }
    }