@Override
  public boolean delete() {
    if(isDir()) for(final IO ch : children()) if(!ch.delete()) return false;
    return file.delete();
  }