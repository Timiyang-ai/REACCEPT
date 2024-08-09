public static byte[] info(final CmdIndexInfo idx, final Data data) {
    switch(idx) {
      case TAG:       return info(INFOTAGS, IndexType.TAG, data, true);
      case ATTNAME:   return info(INFOATTS, IndexType.ATTNAME, data, true);
      case TEXT:      return info(INFOTEXTINDEX, IndexType.TEXT, data,
          data.meta.textindex);
      case ATTRIBUTE: return info(INFOATTRINDEX, IndexType.ATTRIBUTE, data,
          data.meta.attrindex);
      case FULLTEXT:  return info(INFOFTINDEX, IndexType.FULLTEXT, data,
          data.meta.ftindex);
      case PATH:      return info(INFOPATHINDEX, IndexType.PATH, data,
          data.meta.pathindex);
      default:        return Token.token(LI + INDNOTAVL);
    }
  }