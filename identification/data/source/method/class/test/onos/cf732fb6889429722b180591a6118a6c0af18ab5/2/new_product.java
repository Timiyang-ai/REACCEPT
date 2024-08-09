@Override
    public byte[] serialize() {
        int length;
        if (this.dataOffset == 0) {
            this.dataOffset = 5; // default header length
        }
        length = this.dataOffset << 2;
        byte[] payloadData = null;
        if (this.payload != null) {
            this.payload.setParent(this);
            payloadData = this.payload.serialize();
            length += payloadData.length;
        }

        final byte[] data = new byte[length];
        final ByteBuffer bb = ByteBuffer.wrap(data);

        bb.putShort((short) (this.sourcePort & 0xffff));
        bb.putShort((short) (this.destinationPort & 0xffff));
        bb.putInt(this.sequence);
        bb.putInt(this.acknowledge);
        bb.putShort((short) (this.flags | this.dataOffset << 12));
        bb.putShort(this.windowSize);
        bb.putShort(this.checksum);
        bb.putShort(this.urgentPointer);
        if (this.dataOffset > 5) {
            int padding;
            bb.put(this.options);
            padding = (this.dataOffset << 2) - 20 - this.options.length;
            for (int i = 0; i < padding; i++) {
                bb.put((byte) 0);
            }
        }
        if (payloadData != null) {
            bb.put(payloadData);
        }

        if (this.parent != null && this.parent instanceof IPv4) {
            ((IPv4) this.parent).setProtocol(IPv4.PROTOCOL_TCP);
        }

        // compute checksum if needed
        if (this.checksum == 0) {
            bb.rewind();
            int accumulation = 0;

            // compute pseudo header mac
            if (this.parent != null) {
                if (this.parent instanceof IPv4) {
                    final IPv4 ipv4 = (IPv4) this.parent;
                    accumulation += (ipv4.getSourceAddress() >> 16 & 0xffff)
                            + (ipv4.getSourceAddress() & 0xffff);
                    accumulation += (ipv4.getDestinationAddress() >> 16 & 0xffff)
                            + (ipv4.getDestinationAddress() & 0xffff);
                    accumulation += ipv4.getProtocol() & 0xff;
                    accumulation += length & 0xffff;
                } else if (this.parent instanceof IPv6) {
                    final IPv6 ipv6 = (IPv6) this.parent;
                    final int bbLength =
                            Ip6Address.BYTE_LENGTH * 2 // IPv6 src, dst
                                    + 2  // nextHeader (with padding)
                                    + 4; // length
                    final ByteBuffer bbChecksum = ByteBuffer.allocate(bbLength);
                    bbChecksum.put(ipv6.getSourceAddress());
                    bbChecksum.put(ipv6.getDestinationAddress());
                    bbChecksum.put((byte) 0); // padding
                    bbChecksum.put(ipv6.getNextHeader());
                    bbChecksum.putInt(length);
                    bbChecksum.rewind();
                    for (int i = 0; i < bbLength / 2; ++i) {
                        accumulation += 0xffff & bbChecksum.getShort();
                    }
                }
            }

            for (int i = 0; i < length / 2; ++i) {
                accumulation += 0xffff & bb.getShort();
            }
            // pad to an even number of shorts
            if (length % 2 > 0) {
                accumulation += (bb.get() & 0xff) << 8;
            }

            accumulation = (accumulation >> 16 & 0xffff)
                    + (accumulation & 0xffff);
            this.checksum = (short) (~accumulation & 0xffff);
            bb.putShort(16, this.checksum);
        }
        return data;
    }