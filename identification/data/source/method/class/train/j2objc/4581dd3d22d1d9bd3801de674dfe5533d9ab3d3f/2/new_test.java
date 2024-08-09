  public void test_isRegistered() {
    // Regression for HARMONY-45

    // Will contain names of charsets registered with IANA
    Set<String> knownRegisteredCharsets = new HashSet<String>();

    // Will contain names of charsets not known to be registered with IANA
    Set<String> unknownRegisteredCharsets = new HashSet<String>();

    Set<String> names = Charset.availableCharsets().keySet();
    for (Iterator nameItr = names.iterator(); nameItr.hasNext();) {
      String name = (String) nameItr.next();
      if (name.toLowerCase(Locale.ROOT).startsWith("x-")) {
        unknownRegisteredCharsets.add(name);
      } else {
        knownRegisteredCharsets.add(name);
      }
    }

    for (Iterator nameItr = knownRegisteredCharsets.iterator(); nameItr.hasNext();) {
      String name = (String) nameItr.next();
      Charset cs = Charset.forName(name);
      if (!cs.isRegistered()) {
        System.err.println("isRegistered was false for " + name + " " + cs.name() + " " + cs.aliases());
      }
      assertTrue("isRegistered was false for " + name + " " + cs.name() + " " + cs.aliases(), cs.isRegistered());
    }
    for (Iterator nameItr = unknownRegisteredCharsets.iterator(); nameItr.hasNext();) {
      String name = (String) nameItr.next();
      Charset cs = Charset.forName(name);
      assertFalse("isRegistered was true for " + name + " " + cs.name() + " " + cs.aliases(), cs.isRegistered());
    }
  }