public boolean delete() {
    boolean ok = true;
    if(isDir()) for(final IOFile ch : children()) ok &= ch.delete();
    return file.delete() && ok;
  }