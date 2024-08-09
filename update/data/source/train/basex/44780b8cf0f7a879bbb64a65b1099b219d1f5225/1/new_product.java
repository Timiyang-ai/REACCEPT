private void set(final IndexType type, final ValueIndex index) {
    meta.dirty = true;
    switch(type) {
      case TEXT:      textIndex = index; break;
      case ATTRIBUTE: attrIndex = index; break;
      case ATTTOKEN:  attrTokenIndex = index; break;
      case FULLTEXT:  ftxtIndex = index; break;
      default:        break;
    }
  }