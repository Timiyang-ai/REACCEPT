public SingleSignonToken createSSOToken(Token token) throws TokenCreationFailedException {
        try {
            TokenService tokenService = getTokenServiceForType(ssoTokenType);
            SingleSignonTokenImpl ssoToken = new SingleSignonTokenImpl(tokenService);
            ssoToken.initializeToken(token);
            return ssoToken;
        } catch (IllegalArgumentException e) {
            throw new TokenCreationFailedException(e.getMessage(), e);
        }
    }