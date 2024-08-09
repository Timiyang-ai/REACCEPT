public List<TBase<?, ?>> deserialize(byte[] bytes, int offset, int length) throws TException {
        List<TBase<?, ?>> list = new ArrayList<TBase<?, ?>>();
        try {
            trans.reset(bytes, offset, length);

            System.out.println(Thread.currentThread().getName() + " length" + trans.getBytesRemainingInBuffer());
            Header header = readHeader();
            System.out.println(Thread.currentThread().getName() + " first header=" + header);
            if (locator.isChunkHeader(header.getType())) {

                TBase<?, ?> base = null;
                while ((base = deserialize()) != null) {
                    System.out.println(Thread.currentThread().getName() + " deserialized=" + base);
                    System.out.println(Thread.currentThread().getName() + " emain buffer" + trans.getBytesRemainingInBuffer());
                    trans.getBufferPosition();
                    list.add(base);
                }
            } else {
                TBase<?, ?> base = deserialize();
                if (base != null) {
                    list.add(base);
                }
            }

        } finally {
            trans.clear();
            protocol.reset();
        }

        return list;
    }