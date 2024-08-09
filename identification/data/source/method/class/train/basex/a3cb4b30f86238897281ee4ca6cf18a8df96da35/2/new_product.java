private void object() throws QueryIOException {
    consumeWs('{', true);
    conv.openObject();
    if(!consumeWs('}', false)) {
      final TokenSet set = new TokenSet();
      do {
        final byte[] key = !liberal || curr() == '"' ? string() : unquoted();
        final boolean dupl = set.contains(key);
        if(dupl && duplicates == JsonDuplicates.REJECT)
          throw error(BXJS_DUPLICATE_X, "Key '%' occurs more than once.", key);

        conv.openPair(key);
        consumeWs(':', true);
        value();
        conv.closePair(!dupl || duplicates == JsonDuplicates.USE_LAST);
        set.put(key);
      } while(consumeWs(',', false) && !(liberal && curr() == '}'));
      consumeWs('}', true);
    }
    conv.closeObject();
  }