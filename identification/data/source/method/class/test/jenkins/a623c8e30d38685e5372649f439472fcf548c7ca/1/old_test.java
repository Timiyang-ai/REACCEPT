@Test public void doDynamic() throws Exception {
        r.createWebClient().goTo("plugin/credentials/images/24x24/credentials.png", "image/png");
        /* Collapsed somewhere before it winds up in restOfPath:
        r.createWebClient().assertFails("plugin/credentials/images/../images/24x24/credentials.png", HttpServletResponse.SC_BAD_REQUEST);
        */
        r.createWebClient().assertFails("plugin/credentials/images/%2E%2E/images/24x24/credentials.png", HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // IAE from TokenList.<init>
        r.createWebClient().assertFails("plugin/credentials/images/%252E%252E/images/24x24/credentials.png", HttpServletResponse.SC_NOT_FOUND); // SECURITY-131
        r.createWebClient().assertFails("plugin/credentials/images/%25252E%25252E/images/24x24/credentials.png", HttpServletResponse.SC_NOT_FOUND); // just checking
    }