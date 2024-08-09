void append(T logItem) throws IOException {
        Exceptions.checkNotClosed(this.closed.get(), this);
        long seqNo = logItem.getSequenceNumber();
        Exceptions.checkArgument(this.lastSerializedSequenceNumber < seqNo, "logItem",
                "Invalid sequence number. Expected: greater than %d, given: %d.", this.lastSerializedSequenceNumber, seqNo);

        // Remember the last Started SeqNo, in case of failure.
        long previousLastStartedSequenceNumber = this.lastStartedSequenceNumber;
        try {
            // Indicate to the output stream that are about to write a new record.
            this.outputStream.startNewRecord();

            // Completely serialize the entry. Note that this may span more than one Data Frame.
            this.lastStartedSequenceNumber = seqNo;
            logItem.serialize(this.outputStream);

            // Indicate to the output stream that have finished writing the record.
            this.outputStream.endRecord();
            this.lastSerializedSequenceNumber = seqNo;
        } catch (Exception ex) {
            if (this.closed.get()) {
                // It's possible that an async callback resulted in an error and this object got closed after the check
                // at the beginning of this method (which could result in all sorts of errors. If that's the case, we need
                // to indicate that we are closed by throwing ObjectClosedException.
                throw new ObjectClosedException(this, ex);
            } else if (ex instanceof ObjectClosedException) {
                // TargetLog has closed. We need to close too.
                close();
            } else {
                // Discard any information that we have about this record (pretty much revert back to where startNewEntry()
                // would have begun writing).
                this.outputStream.discardRecord();
                this.lastStartedSequenceNumber = previousLastStartedSequenceNumber;
            }

            throw ex;
        }
    }