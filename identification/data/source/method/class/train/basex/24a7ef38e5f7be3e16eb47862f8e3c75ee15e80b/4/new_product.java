private Item write(final File path, final QueryContext ctx,
      final boolean append) throws QueryException {

    if(path.isDirectory()) PATHISDIR.thrw(input, path);

    final Iter ir = expr[1].iter(ctx);
    try {
      final PrintOutput out = PrintOutput.get(
          new FileOutputStream(path, append));
      try {
        final XMLSerializer xml = new XMLSerializer(out,
            FNGen.serialPar(this, 2, ctx));
        for(Item it; (it = ir.next()) != null;) it.serialize(xml);
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