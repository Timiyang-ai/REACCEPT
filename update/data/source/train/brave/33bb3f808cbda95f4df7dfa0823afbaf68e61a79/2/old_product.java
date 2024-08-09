public void setServerSend() {
        if (submitEndAnnotation(Constants.SERVER_SEND, spanCollector())) {
            spanAndEndpoint().state().setCurrentServerSpan(null);
        }
    }