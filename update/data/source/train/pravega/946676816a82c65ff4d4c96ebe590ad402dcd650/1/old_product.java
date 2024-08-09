public void append(Operation operation) throws IOException {
        Exceptions.throwIfClosed(this.closed, this);

        // TODO: consider checking Operation.getSequenceNumber() monotonicity. Make sure it sticks across multiple instances.
        long previousLastStartedSequenceNumber = this.lastStartedSequenceNumber;
        try {
            // Indicate to the output stream that are about to write a new record.
            this.outputStream.startNewRecord();

            // Completely serialize the entry. Note that this may span more than one Data Frame.
            this.lastStartedSequenceNumber = operation.getSequenceNumber();
            operation.serialize(this.outputStream);
            this.lastSerializedSequenceNumber = operation.getSequenceNumber();
        }
        catch (IOException ex) {
            // Discard any information that we have about this record (pretty much revert back to where startNewEntry() would have begun writing).
            // The try-catch inside handleDataFrameComplete() deals with the DataFrame-level handling; here we just deal with this operation.
            this.outputStream.discardRecord();
            this.lastSerializedSequenceNumber = previousLastStartedSequenceNumber;
            throw ex;
        }

        // Indicate to the output stream that have finished writing the record.
        this.outputStream.endRecord();
    }