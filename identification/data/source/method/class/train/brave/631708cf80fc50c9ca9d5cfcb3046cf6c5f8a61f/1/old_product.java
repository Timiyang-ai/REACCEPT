public void setServerSend() {
        if (submitEndAnnotation(Constants.SERVER_SEND)) {
            spanAndEndpoint().state().setCurrentServerSpan(null);
        }
    }