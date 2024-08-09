public void setClientReceived() {
        if (submitEndAnnotation(Constants.CLIENT_RECV, spanCollector())) {
            spanAndEndpoint().state().setCurrentClientSpan(null);
        }
    }