@Test
  public void hash() {
    query("string(" + _HASH_HASH.args("", "MD5") + ")", "1B2M2Y8AsgTpgAmY7PhCfg==");
    query("string(" + _HASH_HASH.args("", "md5") + ")", "1B2M2Y8AsgTpgAmY7PhCfg==");
    query("string(" + _HASH_HASH.args("", "SHA") + ")", "2jmj7l5rSw0yVb/vlWAYkK/YBwk=");
    query("string(" + _HASH_HASH.args("", "SHA1") + ")", "2jmj7l5rSw0yVb/vlWAYkK/YBwk=");
    query("string(" + _HASH_HASH.args("", "SHA-1") + ")", "2jmj7l5rSw0yVb/vlWAYkK/YBwk=");
    query("string(" + _HASH_HASH.args("", "SHA-256") + ")",
        "47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=");
    query("string(" + _HASH_HASH.args(" xs:base64Binary('')", "md5") + ")",
        "1B2M2Y8AsgTpgAmY7PhCfg==");
    query("string(" + _HASH_HASH.args(" xs:hexBinary('')", "md5") + ")",
        "1B2M2Y8AsgTpgAmY7PhCfg==");

    final String file = sandbox() + "hash";
    try {
      query(_FILE_WRITE.args(file, "BaseX"));
      query("string(" + _HASH_HASH.args(_FILE_READ_BINARY.args(file), "md5") + ")",
          "DWUYXJ4pYxHAoiABeeR5og==");
    } finally {
      query(_FILE_DELETE.args(file));
    }

    error(_HASH_HASH.args("", ""), HASH_ALGORITHM_X);
  }