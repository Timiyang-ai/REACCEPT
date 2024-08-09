    @Test
    public void publishToken() {
        String bearer = sut.publishToken(server, new SUser(vutUserId));
        UserToken fromBearer = UserToken.fromBearerToken(bearer);
        assertEquals(vutUserId, fromBearer.getUserId());
    }