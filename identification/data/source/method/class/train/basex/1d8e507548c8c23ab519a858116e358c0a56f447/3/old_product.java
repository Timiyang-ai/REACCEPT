private static byte[] info(final CmdIndexInfo idx, final Data data) {
    switch(idx) {
      case TAG:
        return info(INFOTAGS, IndexType.TAG, data);
      case ATTNAME:
        return info(INFOATTS, IndexType.ATTNAME, data);
      case TEXT:
        return !data.meta.textindex ? Token.EMPTY :
          info(INFOTEXTINDEX, IndexType.TEXT, data);
      case ATTRIBUTE:
        return !data.meta.attrindex ? Token.EMPTY :
          info(INFOATTRINDEX, IndexType.ATTRIBUTE, data);
      case FULLTEXT:
        return !data.meta.ftindex ? Token.EMPTY :
          info(INFOFTINDEX, IndexType.FULLTEXT, data);
      case PATH:
        return !data.meta.pathindex ? Token.EMPTY :
          info(INFOPATHINDEX, IndexType.PATH, data);
      default:
        return Token.EMPTY;
    }
  }