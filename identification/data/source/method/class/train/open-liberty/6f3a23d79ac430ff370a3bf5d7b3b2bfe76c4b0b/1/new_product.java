@FFDCIgnore(InvalidTokenException.class)
    public abstract Token recreateTokenFromBytes(byte[] tokenBytes) throws InvalidTokenException, TokenExpiredException;