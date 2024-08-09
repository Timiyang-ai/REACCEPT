@Override
    public Token createToken(String tokenType, Map<String, Object> tokenData) throws TokenCreationFailedException {
        try {
            TokenService tokenService = getTokenServiceForType(tokenType);
            return tokenService.createToken(tokenData);
        } catch (IllegalArgumentException e) {
            throw new TokenCreationFailedException(e.getMessage(), e);
        }
    }