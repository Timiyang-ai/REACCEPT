private static void optimize(final IndexType type, final Data d,
      final boolean create, final boolean old, final Optimize c) throws IOException {

    // check if flags are nothing has changed
    if(create == old) return;

    // create or drop index
    if(create) create(type, d, c);
    else drop(type, d);
  }