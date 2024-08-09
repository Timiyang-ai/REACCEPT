@Test
  public void hash() {
    query(_HASH_HASH.args("", "MD5"), "1B2M2Y8AsgTpgAmY7PhCfg==");
    query(_HASH_HASH.args("", "md5"), "1B2M2Y8AsgTpgAmY7PhCfg==");
    query(_HASH_HASH.args("", "SHA"), "2jmj7l5rSw0yVb/vlWAYkK/YBwk=");
    query(_HASH_HASH.args("", "SHA1"), "2jmj7l5rSw0yVb/vlWAYkK/YBwk=");
    query(_HASH_HASH.args("", "SHA-1"), "2jmj7l5rSw0yVb/vlWAYkK/YBwk=");
    query(_HASH_HASH.args("", "SHA-256"), "47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=");
    query(_HASH_HASH.args("xs:base64Binary('')", "md5"), "1B2M2Y8AsgTpgAmY7PhCfg==");
    query(_HASH_HASH.args("xs:hexBinary('')", "md5"), "1B2M2Y8AsgTpgAmY7PhCfg==");
    error(_HASH_HASH.args("", ""), Err.HASH_ALG);
  }