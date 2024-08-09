    @Test
    public void logUserOut() throws Exception {
        Response response = authenticationResource.logUserOut(TOKENHEADER);
        assert (response.getStatus() == 204);
    }