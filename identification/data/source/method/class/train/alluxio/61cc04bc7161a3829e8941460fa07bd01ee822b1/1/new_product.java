@Override
  public synchronized FileInfo generateClientFileInfo(String path) {
    FileInfo ret = new FileInfo();
    ret.setFileId(getId());
    ret.setName(getName());
    ret.setPath(path);
    ret.setLength(0);
    ret.setBlockSizeBytes(0);
    ret.setCreationTimeMs(getCreationTimeMs());
    ret.setCompleted(true);
    ret.setFolder(true);
    ret.setPinned(isPinned());
    ret.setCacheable(false);
    ret.setPersisted(isPersisted());
    ret.setLastModificationTimeMs(getLastModificationTimeMs());
    ret.setTtl(Constants.NO_TTL);
    ret.setUserName(getUserName());
    ret.setGroupName(getGroupName());
    ret.setPermission(getPermission());
    ret.setPersistenceState(getPersistenceState().toString());
    return ret;
  }