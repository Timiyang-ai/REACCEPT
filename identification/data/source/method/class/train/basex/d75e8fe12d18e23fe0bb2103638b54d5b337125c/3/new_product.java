private Item delete(final File path, final QueryContext ctx) throws QueryException {
    if(!path.exists()) FL_WHICH.thrw(info, path.getAbsolutePath());
    if(optionalBool(1, ctx)) deleteRec(path);
    else if(!path.delete()) FL_DEL.thrw(info, path);
    return null;
  }