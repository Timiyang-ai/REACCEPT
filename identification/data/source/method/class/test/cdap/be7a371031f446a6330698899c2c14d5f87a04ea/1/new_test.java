@Test
  public void testUsage() throws Exception {

    // argument combinations that should return success
    String[][] goodArgsList = {
        { "--help" }, // print help

        { "read", "--key", "cat" }, // simple key
        { "read", "--key", "k\u00eby", "--encoding", "Latin1" }, // non-ascii
                                                   // key with latin1 encoding
        { "read", "--key", "636174", "--hex" }, // "cat" in hex notation
        { "read", "--key", "6beb79", "--hex" }, // non-Ascii "këy" in hex
                                                // notation
        { "read", "--key", "cat", "--base",
            "http://localhost:" + port + prefix + path }, // explicit base url
        { "read", "--key", "cat", "--host", "localhost" }, // correct hostname
        { "read", "--key", "cat", "--connector", name }, // valid connector name

        { "list" },
        { "list", "--url" },
        { "list", "--hex" },
        { "list", "--encoding", "Latin1" },

        { "write", "--key", "pfunk", "--value", "the cat" },
        { "write", "--key", "c\u00e4t", "--value",
                "pf\u00fcnk", "--encoding", "Latin1" }, // non-Ascii cät=pfünk
        { "write", "--key", "cafebabe", "--value", "deadbeef", "--hex" }, // hex

        // delete the value just written
        { "delete", "--key", "pfunk" },
        { "delete", "--key", "c\u00e4t", "--encoding", "Latin1" },
        { "delete", "--key", "cafebabe", "--hex" },

    };

    // argument combinations that should lead to failure
    String[][] badArgsList = {
        { },
        { "read", "--key" }, // no key
        { "read", "--garble" }, // invalid argument
        { "read", "--encoding" }, // missing argument
        { "read", "--key-file" }, // missing argument
        { "read", "--value-file" }, // missing argument
        { "read", "--base" }, // missing argument
        { "read", "--host" }, // missing argument
        { "read", "--connector" }, // missing argument
        { "read", "--connector", "fantasy.name" }, // invalid connector name
        { "read", "--key", "funk", "--hex" }, // non-hexadecimal key with --hex
        { "read", "--key", "babed", "--hex" }, // key of odd length with --hex
        { "read", "--key", "pfunk", "--encoding", "fantasy string" }, // invalid
                                                                     // encoding
        { "read", "--key", "k\u00eby", "--ascii" }, // non-ascii key with
                              // --ascii. Note that this drops the msb of the ë
                              // and hence uses "key" as the key -> 404
        { "read", "--key", "key with blanks", "--url" }, // url-encoded key may
                                                          // not contain blanks
        { "read", "--key", "cat", "--base",
            "http://localhost" + prefix + path }, // explicit but port is
                                              // missing -> connection refused
        { "read", "--key", "cat", "--base",
            "http://localhost:" + port + "/gataca" + path }, // explicit but
                                                            // wrong base -> 404
        { "read", "--key", "cat", "--host", "my.fantasy.hostname" }, // bad host
                                                                  // name -> 404
        { "read", "--host", "localhost" }, // no key given

        { "list", "--encoding" }, // missing encoding
        { "list", "--key", "pfunk" }, // key not allowed
        { "list", "--value", "the cat" }, // value not allowed for list

        { "delete" }, // key missing
        { "delete", "--key", "pfunks", "--hex" }, // not a hex string
        { "delete", "--key", "cafebab", "--hex" }, // not a hex string
        { "delete", "--key", "cafe babe", "--url" }, // url string can't have
                                                     // blank
        { "delete", "--value", "cafe babe" }, // can't delete by value

    };

    // test each good combination
    for (String[] args : goodArgsList) {
      LOG.info("Testing: " + Arrays.toString(args));
      Assert.assertNotNull(new DataClient().execute(args, configuration));
    }
    // test each bad combination
    for (String[] args : badArgsList) {
      LOG.info("Testing: " + Arrays.toString(args));
      Assert.assertNull(new DataClient().execute(args, configuration));
    }
  }