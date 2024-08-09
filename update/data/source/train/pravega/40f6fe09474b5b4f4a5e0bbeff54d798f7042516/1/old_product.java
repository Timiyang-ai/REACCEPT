<T extends OperationProcessor> long getHighestCommittedSequenceNumber(Iterable<T> processors) {
        long lowestUncommittedSeqNo = Long.MAX_VALUE;
        for (OperationProcessor a : processors) {
            if (!a.isClosed()) {
                long firstSeqNo = a.getLowestUncommittedSequenceNumber();
                if (firstSeqNo >= 0) {
                    // Subtract 1 from the computed LUSN and then make sure it doesn't exceed the LastReadSequenceNumber
                    // (it would only exceed it if there are no aggregators or of they are all empty - which means we processed everything).
                    lowestUncommittedSeqNo = Math.min(lowestUncommittedSeqNo, firstSeqNo - 1);
                }
            }
        }

        lowestUncommittedSeqNo = Math.min(lowestUncommittedSeqNo, this.state.getLastReadSequenceNumber());
        return lowestUncommittedSeqNo;
    }