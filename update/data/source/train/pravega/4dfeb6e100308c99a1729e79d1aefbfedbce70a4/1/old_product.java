public void complete() {
        long seqNo = this.operation.getSequenceNumber();
        Preconditions.checkState(!this.operation.canSerialize() || seqNo >= 0,
                "About to complete a CompletableOperation that has no sequence number.");

        this.done = true;
        if (this.successHandler != null) {
            CallbackHelpers.invokeSafely(this.successHandler, seqNo, cex -> log.error("Success Callback invocation failure.", cex));
        }
    }