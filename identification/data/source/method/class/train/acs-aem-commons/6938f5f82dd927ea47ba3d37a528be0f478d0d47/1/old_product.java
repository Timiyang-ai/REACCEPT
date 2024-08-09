private static int dropCookies(final HttpServletResponse response, final Cookie[] cookies, final String cookiePath) {
        int count = 0;

        for (final Cookie cookie : cookies) {
            if (cookie == null) {
                continue;
            }

            cookie.setMaxAge(0);
            cookie.setPath(cookiePath);

            addCookie(cookie, response);
            count++;
        }

        return count;
    }