@Override
    public boolean isMatchingKey(AbstractKey key) {
        KeyAddress other = new KeyAddress(key, 0, _isLong);
        if (other.keyMask != keyMask)
            return false;
        if (!Arrays.equals(keyDigest, other.keyDigest))
            return false;
        return true;
    }