final B64 hash(final String algo, final QueryContext qc) throws QueryException {
    final Item it = exprs[0].atomItem(qc, info);
    try {
      final MessageDigest md = MessageDigest.getInstance(algo);
      if(it instanceof B64Lazy) {
        try(BufferInput bi = it.input(info)) {
          final byte[] tmp = new byte[IO.BLOCKSIZE];
          do {
            final int n = bi.read(tmp);
            if(n == -1) return B64.get(md.digest());
            md.update(tmp, 0, n);
            qc.checkStop();
          } while(true);
        } catch(final IOException ex) {
          throw FILE_IO_ERROR_X.get(info, ex);
        }
      }
      // non-streaming item, string
      return B64.get(md.digest(toBytes(it)));
    } catch(final NoSuchAlgorithmException ex) {
      Util.debug(ex);
      throw HASH_ALGORITHM_X.get(info, algo);
    }
  }