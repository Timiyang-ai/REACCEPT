void replaceLastFile(FileDescriptor fileDescriptor) {
        synchronized (this.files) {
            Preconditions.checkState(this.files.size() > 0, "Insufficient number of files in the handle to perform this operation.");
            int lastIndex = this.files.size() - 1;
            FileDescriptor lastFile = this.files.get(lastIndex);

            long expectedOffset = lastFile.getOffset();
            Preconditions.checkArgument(fileDescriptor.getOffset() == expectedOffset,
                    "Invalid offset. Expected %s, actual %s.", expectedOffset, fileDescriptor.getOffset());

            long expectedMinEpoch = lastFile.getEpoch();
            Preconditions.checkArgument(fileDescriptor.getEpoch() >= expectedMinEpoch,
                    "Invalid epoch. Expected at least %s, actual %s.", expectedMinEpoch, fileDescriptor.getEpoch());

            this.files.remove(lastIndex);
            this.files.add(fileDescriptor);
        }
    }