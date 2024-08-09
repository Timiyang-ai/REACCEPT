@Test
    public void getCookieValue_existingCookieDifferentCase() {
        String name = "cookieName";
        String value = "cookieValue";
        Cookie[] cookies = new Cookie[] { new Cookie(name, value) };
        assertEquals(value, CookieHelper.getCookieValue(cookies, "COOKIENAME"));
    }