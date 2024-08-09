@Override
    public Token recreateTokenFromBytes(byte[] tokenBytes, String... removeAttributes) throws InvalidTokenException, TokenExpiredException {
        TokenFactory tokenFactory = ltpaConfig.getTokenFactory();
        Token token = tokenFactory.validateTokenBytes(tokenBytes, removeAttributes);
        return token;
    }