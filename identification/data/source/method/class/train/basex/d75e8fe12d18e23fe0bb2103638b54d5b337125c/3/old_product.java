private Item delete(final File path, final QueryContext ctx) throws QueryException {
    if(!path.exists()) PATHNOTEXISTS.thrw(info, path.getAbsolutePath());
    if(optionalBool(1, ctx)) deleteRec(path);
    else if(!path.delete()) CANNOTDEL.thrw(info, path);
    return null;
  }