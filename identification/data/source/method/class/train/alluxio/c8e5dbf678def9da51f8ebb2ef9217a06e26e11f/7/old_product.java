@Override
  public FileInfo generateClientFileInfo(String path) {
    FileInfo ret = new FileInfo();
    ret.setFileId(getId());
    ret.setName(getName());
    ret.setPath(path);
    ret.setBlockSizeBytes(0);
    ret.setCreationTimeMs(getCreationTimeMs());
    ret.setCompleted(true);
    ret.setFolder(isDirectory());
    ret.setPinned(isPinned());
    ret.setCacheable(false);
    ret.setPersisted(isPersisted());
    ret.setLastModificationTimeMs(getLastModificationTimeMs());
    ret.setTtl(mTtl);
    ret.setTtlAction(mTtlAction);
    ret.setOwner(getOwner());
    ret.setGroup(getGroup());
    ret.setMode(getMode());
    ret.setPersistenceState(getPersistenceState().toString());
    ret.setMountPoint(isMountPoint());
    ret.setUfsFingerprint(Constants.INVALID_UFS_FINGERPRINT);
    ret.setAcl(mAcl);
    ret.setDefaultAcl(mDefaultAcl);
    return ret;
  }