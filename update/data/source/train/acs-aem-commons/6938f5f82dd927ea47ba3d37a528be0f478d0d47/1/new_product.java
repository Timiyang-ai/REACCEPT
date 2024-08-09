private static int dropCookies(final HttpServletResponse response, final Cookie[] cookies, final String cookiePath) {
        int count = 0;

        for (final Cookie cookie : cookies) {
            if (cookie == null) {
                continue;
            }

            final Cookie responseCookie = (Cookie) cookie.clone();
            responseCookie.setMaxAge(0);
            responseCookie.setPath(cookiePath);
            responseCookie.setValue("");

            addCookie(responseCookie, response);
            count++;
        }

        return count;
    }