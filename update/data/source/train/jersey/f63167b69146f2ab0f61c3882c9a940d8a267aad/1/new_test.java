@Test
    public void testExecutorAsync() {
        final Response response = target().path("ResponseTest/executorAsync").request().get(Response.class);
        final String location = response.getHeaderString(HttpHeaders.LOCATION);
        LOGGER.info("Location resolved from response > " + location);
        assertFalse("The comparison failed in the resource method.", executorComparisonFailed.get());
        assertEquals(getBaseUri() + "ResponseTest/executorAsync", location);
    }