@Override
    public SingleSignonToken createSSOToken(Map<String, Object> tokenData) throws TokenCreationFailedException {
        try {
            TokenService tokenService = getTokenServiceForType(ssoTokenType);
            SingleSignonTokenImpl ssoToken = new SingleSignonTokenImpl(tokenService);
            Token ssoLtpaToken = tokenService.createToken(tokenData);
            ssoToken.initializeToken(ssoLtpaToken);
            return ssoToken;
        } catch (IllegalArgumentException e) {
            throw new TokenCreationFailedException(e.getMessage(), e);
        }
    }