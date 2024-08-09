boolean add(final NameTest test) {
    for(final NameTest t : tests) if(t.equals(test)) return false;
    tests.add(test);
    return true;
  }