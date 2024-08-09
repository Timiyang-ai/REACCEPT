public void setClientReceived() {
        if (submitEndAnnotation(Constants.CLIENT_RECV, reporter())) {
            spanAndEndpoint().state().setCurrentClientSpan(null);
        }
    }