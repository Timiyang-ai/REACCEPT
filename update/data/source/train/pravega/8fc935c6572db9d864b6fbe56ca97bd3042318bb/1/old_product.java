public void append(T logItem) throws IOException {
        Exceptions.checkNotClosed(this.closed, this);
        long seqNo = logItem.getSequenceNumber();
        Exceptions.checkArgument(this.lastSerializedSequenceNumber < seqNo, "logItem", "Invalid sequence number. Expected: greater than %d, given: %d.", this.lastSerializedSequenceNumber, seqNo);

        // Remember the last Started SeqNo, in case of failure.
        long previousLastStartedSequenceNumber = this.lastStartedSequenceNumber;
        try {
            // Indicate to the output stream that are about to write a new record.
            this.outputStream.startNewRecord();

            // Completely serialize the entry. Note that this may span more than one Data Frame.
            this.lastStartedSequenceNumber = seqNo;
            logItem.serialize(this.outputStream);
            this.lastSerializedSequenceNumber = seqNo;
        } catch (Exception ex) {
            // Discard any information that we have about this record (pretty much revert back to where startNewEntry() would have begun writing).
            // The try-catch inside handleDataFrameComplete() deals with the DataFrame-level handling; here we just deal with this LogItem.
            this.outputStream.discardRecord();
            this.lastStartedSequenceNumber = previousLastStartedSequenceNumber;
            throw ex;
        }

        // Indicate to the output stream that have finished writing the record.
        this.outputStream.endRecord();
    }