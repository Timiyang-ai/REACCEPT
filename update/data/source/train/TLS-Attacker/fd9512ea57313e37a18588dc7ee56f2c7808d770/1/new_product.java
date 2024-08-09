public byte[] receiveRawBytes() throws IOException {
        return context.getTransportHandler().fetchData();
    }