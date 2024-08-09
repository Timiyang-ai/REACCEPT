public void setMaxSize(int maxSize) throws TwilioRestException {
        Map<String, String> vars = new HashMap<String, String>();
        String maxSizeString = Integer.toString(maxSize);
        vars.put(Queue.MAX_SIZE, maxSizeString);
        TwilioRestResponse response = this.getClient().safeRequest(this.getResourceLocation(), "POST", vars);
        if (response.isError()) {
            throw new IllegalStateException("Response indicated error:" + response.getResponseText());
        }
        // if we reached this point, store it in our own set of properties, i.e. we don't have to load the instance
        // just to get to this property
        this.setProperty(Queue.MAX_SIZE, maxSize);
    }