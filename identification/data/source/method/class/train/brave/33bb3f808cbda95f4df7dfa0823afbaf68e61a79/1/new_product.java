public void setServerSend() {
        if (submitEndAnnotation(Constants.SERVER_SEND, reporter())) {
            spanAndEndpoint().state().setCurrentServerSpan(null);
        }
    }