public byte[] recieveRawBytes() throws IOException {
        return context.getTransportHandler().fetchData();
    }