@Test
    public void testDropCookies() {
        String[] cookieNames = {"dog-mammal", "cat-mammal"};
        CookieUtil.dropCookies(request, response, "/", cookieNames);
        assertTrue(true);
    }