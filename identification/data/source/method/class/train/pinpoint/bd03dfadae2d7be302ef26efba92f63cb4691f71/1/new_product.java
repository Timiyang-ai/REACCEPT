public List<TBase<?, ?>> deserialize(byte[] bytes, int offset, int length) throws TException {
        List<TBase<?, ?>> list = new ArrayList<TBase<?, ?>>();
        try {
            trans.reset(bytes, offset, length);

            Header header = readHeader();
            if (locator.isChunkHeader(header.getType())) {

                TBase<?, ?> base = null;
                while ((base = deserialize()) != null) {
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