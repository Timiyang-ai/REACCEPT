    @Test
    public void validateToken() {
        String bearer = sut.publishToken(server, new SUser(vutUserId));
        UserToken fromBearer = UserToken.fromBearerToken(bearer);

        sut.validateToken(fromBearer);
    }