    @Test
    public void rewriteSequenceNumber()
        throws Exception
    {
        ResumableStreamRewriter snr = new ResumableStreamRewriter();
        assertEquals(0, snr.seqnumDelta);
        assertEquals(-1, snr.highestSequenceNumberSent);

        // Accept first packet.
        int ret = snr.rewriteSequenceNumber(true, 0xffff - 2);
        assertEquals(0, snr.seqnumDelta);
        assertEquals(0xffff - 2, snr.highestSequenceNumberSent);
        assertEquals(ret, 0xffff - 2);

        // Retransmission.
        ret = snr.rewriteSequenceNumber(true, 0xffff - 2);
        assertEquals(0, snr.seqnumDelta);
        assertEquals(0xffff - 2, snr.highestSequenceNumberSent);
        assertEquals(ret, 0xffff - 2);

        // Retransmission & accept toggle.
        snr.rewriteSequenceNumber(false, 0xffff - 2);
        assertEquals(0, snr.seqnumDelta);
        assertEquals(0xffff - 2, snr.highestSequenceNumberSent);

        // Retransmission & accept toggle.
        ret = snr.rewriteSequenceNumber(true, 0xffff - 2);
        assertEquals(0, snr.seqnumDelta);
        assertEquals(0xffff - 2, snr.highestSequenceNumberSent);
        assertEquals(ret, 0xffff - 2);

        // Drop ordered packet.
        snr.rewriteSequenceNumber(false, 0xffff - 1);
        assertEquals(1, snr.seqnumDelta);
        assertEquals(0xffff - 2, snr.highestSequenceNumberSent);

        // Drop re-ordered packet.
        snr.rewriteSequenceNumber(false, 0xffff - 3);
        assertEquals(1, snr.seqnumDelta);
        assertEquals(0xffff - 2, snr.highestSequenceNumberSent);

        // Accept after re-ordered drop.
        ret = snr.rewriteSequenceNumber(true, 0xffff);
        assertEquals(1, snr.seqnumDelta);
        assertEquals(0xffff - 1, snr.highestSequenceNumberSent);
        assertEquals(0xffff - 1, ret);

        // Drop ordered packet.
        snr.rewriteSequenceNumber(false, 0);
        assertEquals(2, snr.seqnumDelta);
        assertEquals(0xffff - 1, snr.highestSequenceNumberSent);

        // Accept ordered packet.
        ret = snr.rewriteSequenceNumber(true, 1);
        assertEquals(2, snr.seqnumDelta);
        assertEquals(0xffff, snr.highestSequenceNumberSent);
        assertEquals(ret, 0xffff);

        // Drop ordered packets.
        for (int i = 2; i < 0xffff; i++)
        {
            snr.rewriteSequenceNumber(false, i);
            assertEquals(i + 1, snr.seqnumDelta);
            assertEquals(0xffff, snr.highestSequenceNumberSent);
        }

        // Drop ordered packet.
        snr.rewriteSequenceNumber(false, 0xffff);
        assertEquals(0, snr.seqnumDelta);
        assertEquals(0xffff, snr.highestSequenceNumberSent);

        // Accept ordered packet.
        ret = snr.rewriteSequenceNumber(true, 0);
        assertEquals(0, snr.seqnumDelta);
        assertEquals(0, snr.highestSequenceNumberSent);
        assertEquals(ret, 0);

        // Retransmission + accept toggle
        ret = snr.rewriteSequenceNumber(true, 0xffff);
        assertEquals(0, snr.seqnumDelta);
        assertEquals(0, snr.highestSequenceNumberSent);
        assertEquals(ret, 0xffff);
    }