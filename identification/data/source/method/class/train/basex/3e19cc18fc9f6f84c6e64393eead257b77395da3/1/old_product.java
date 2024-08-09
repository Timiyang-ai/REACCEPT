private B64 hash(final String algo, final QueryContext ctx) throws QueryException {
    final Item it = checkItem(expr[0], ctx);
    final byte[] val;
    if(it instanceof AStr) {
      val = it.string(info);
    } else if(it instanceof B64) {
      val = ((B64) it).binary(info);
    } else {
      throw STRB64TYPE.thrw(info, it.type);
    }
    return hashBinary(val, algo);
  }