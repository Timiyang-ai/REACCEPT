private Item write(final File path, final QueryContext ctx)
      throws QueryException {

    final boolean append = optionalBool(3, ctx);
    if(path.isDirectory()) PATHISDIR.thrw(input, path);

    final Iter ir = expr[1].iter(ctx);
    try {
      final PrintOutput out = new PrintOutput(
          new BufferedOutputStream(new FileOutputStream(path, append)));
      try {
        final XMLSerializer xml = new XMLSerializer(out,
            FNGen.serialPar(this, 2, ctx));
        Item it;
        while((it = ir.next()) != null) it.serialize(xml);
        xml.close();
      } catch(final SerializerException ex) {
        throw new QueryException(input, ex);
      } finally {
        out.close();
      }
    } catch(final IOException ex) {
      FILEERROR.thrw(input, ex);
    }
    return null;
  }