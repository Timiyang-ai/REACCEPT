private static void optimize(final IndexType type, final Data d, final boolean create,
      final boolean old, final boolean rebuild, final Optimize cmd) throws IOException {

    // check if flags are nothing has changed
    if(!rebuild && create == old) return;

    // create or drop index
    if(create) create(type, d, cmd);
    else drop(type, d);
  }