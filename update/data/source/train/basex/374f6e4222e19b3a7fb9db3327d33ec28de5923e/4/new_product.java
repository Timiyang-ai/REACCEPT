boolean add(final NameTest test) {
    for(final NameTest nm : tests) if(nm.equals(test)) return false;
    tests.add(test);
    return true;
  }