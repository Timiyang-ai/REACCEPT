    private void executeTests() {
        actionPerform();
        DataResult dr = (DataResult) request.getAttribute(RequestContext.PAGE_LIST);
        assertNotNull(dr);
        assertTrue(dr.size() > 0);
    }