@FFDCIgnore(InvalidTokenException.class)
    public abstract Token recreateTokenFromBytes(byte[] tokenBytes, String... removeAttributes) throws InvalidTokenException, TokenExpiredException;