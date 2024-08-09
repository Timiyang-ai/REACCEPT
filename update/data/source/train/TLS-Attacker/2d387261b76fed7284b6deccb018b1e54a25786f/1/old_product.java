public String receiveString() throws IOException {
        return new String(receiveBytes());
    }