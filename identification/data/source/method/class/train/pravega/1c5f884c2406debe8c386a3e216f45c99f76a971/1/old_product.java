public void seal() {
        if (!this.sealed && !this.contents.isReadOnly()) {
            Preconditions.checkState(writeEntryStartIndex < 0, "An open entry exists. Any open entries must be closed prior to sealing.");

            this.header.setContentLength(writePosition);
            this.header.commit();
            this.sealed = true;
        }
    }