int rewriteSequenceNumber(boolean accept, int sequenceNumber)
    {
        if (accept)
        {
            // overwrite the sequence number (if needed)
            int newSequenceNumber
                = RTPUtils.subtractNumber(sequenceNumber, seqnumDelta);

            // init or update the highest sent sequence number (if needed)
            if (highestSequenceNumberSent == -1 || RTPUtils.sequenceNumberDiff(
                newSequenceNumber, highestSequenceNumberSent) > 0)
            {
                highestSequenceNumberSent = newSequenceNumber;
            }

            return newSequenceNumber;
        }
        else
        {
            // update the sequence number delta (if needed)
            if (highestSequenceNumberSent != -1)
            {
                final int newDelta = RTPUtils.subtractNumber(
                    sequenceNumber, highestSequenceNumberSent);

                if (RTPUtils.sequenceNumberDiff(newDelta, seqnumDelta) > 0)
                {
                    seqnumDelta = newDelta;
                }
            }

            return sequenceNumber;
        }
    }