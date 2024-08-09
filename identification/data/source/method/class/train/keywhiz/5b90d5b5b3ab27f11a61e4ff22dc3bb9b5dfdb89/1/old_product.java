public byte[] expand(SecretKey key, @Nullable byte[] info, int outputLength) {
    requireNonNull(key, "key must not be null");
    if (outputLength < 1) {
      throw new IllegalArgumentException("outputLength must be positive");
    }
    int hashLen = hash.getByteLength();
    if (outputLength > 255 * hashLen) {
      throw new IllegalArgumentException("outputLength must be less than or equal to 255*HashLen");
    }

    if (info == null) {
      info = new byte[0];
    }

    /*
    The output OKM is calculated as follows:

      N = ceil(L/HashLen)
      T = T(1) | T(2) | T(3) | ... | T(N)
      OKM = first L bytes of T

    where:
      T(0) = empty string (zero length)
      T(1) = HMAC-Hash(PRK, T(0) | info | 0x01)
      T(2) = HMAC-Hash(PRK, T(1) | info | 0x02)
      T(3) = HMAC-Hash(PRK, T(2) | info | 0x03)
      ...
     */
    int n = (outputLength % hashLen == 0) ?
        outputLength / hashLen :
        (outputLength / hashLen) + 1;

    byte[] hashRound = new byte[0];

    ByteBuffer generatedBytes = ByteBuffer.allocate(Math.multiplyExact(n, hashLen));
    Mac mac = initMac(key);
    for (int roundNum = 1; roundNum <= n; roundNum++) {
      mac.reset();
      mac.update(hashRound);
      mac.update(info);
      mac.update((byte) roundNum);
      hashRound = mac.doFinal();
      generatedBytes.put(hashRound);
    }

    byte[] result = new byte[outputLength];
    generatedBytes.rewind();
    generatedBytes.get(result, 0, outputLength);
    return result;
  }