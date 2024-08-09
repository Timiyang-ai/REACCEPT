public Mode applyFileUMask() {
    applyUMask(Mode.getUMask());
    applyUMask(FILE_UMASK);
    return this;
  }