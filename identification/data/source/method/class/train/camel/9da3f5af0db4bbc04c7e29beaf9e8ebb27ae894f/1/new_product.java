@InvokeOnHeader(Web3jConstants.NET_LISTENING)
    void netListening(Message message) throws IOException {
        Request<?, NetListening> netListeningRequest = web3j.netListening();
        setRequestId(message, netListeningRequest);
        NetListening response = netListeningRequest.send();
        boolean hasError = checkForError(message, response);
        if (!hasError) {
            message.setBody(response.isListening());
        }
    }