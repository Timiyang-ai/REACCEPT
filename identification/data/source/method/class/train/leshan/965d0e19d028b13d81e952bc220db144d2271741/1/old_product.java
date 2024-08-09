public static ByteBuffer encode(Tlv[] tlvs) {
        int size = 0;

        LOG.trace("start");
        for (Tlv tlv : tlvs) {

            int length = tlvEncodedLength(tlv);
            size += tlvEncodedSize(tlv, length);
            LOG.trace("tlv size : {}", size);
        }
        LOG.trace("done, size : {}", size);
        ByteBuffer b = ByteBuffer.allocate(size);
        b.order(ByteOrder.BIG_ENDIAN);
        for (Tlv tlv : tlvs) {
            encode(tlv, b);
        }
        b.flip();
        return b;
    }