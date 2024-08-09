public void process() {
        JMeterContext context = getThreadContext();
        Sampler sam = context.getCurrentSampler();
        SampleResult res = context.getPreviousResult();
        HTTPSamplerBase sampler;
        HTTPSampleResult result;
        if (res == null || !(sam instanceof HTTPSamplerBase) || !(res instanceof HTTPSampleResult)) {
            log.info("Can't apply HTML Link Parser when the previous" + " sampler run is not an HTTP Request.");
            return;
        } else {
            sampler = (HTTPSamplerBase) sam;
            result = (HTTPSampleResult) res;
        }
        String responseText; // $NON-NLS-1$
        responseText = result.getResponseDataAsString();
        Document html;
        int index = responseText.indexOf("<"); // $NON-NLS-1$
        if (index == -1) {
            index = 0;
        }
        if (log.isDebugEnabled()) {
            log.debug("Check for matches against: " + sampler.toString());
        }
        html = (Document) HtmlParsingUtils.getDOM(responseText.substring(index));
        addAnchorUrls(html, result, sampler);
        addFormUrls(html, result, sampler);
        addFramesetUrls(html, result, sampler);
        if (hasNotVisited()) {
            HTTPSamplerBase url = getNextLink();
            if (log.isDebugEnabled()) {
                log.debug("Selected: " + url.toString());
            }
            sampler.setDomain(url.getDomain());
            sampler.setPath(url.getPath());
            if (url.getMethod().equals(HTTPConstants.POST)) {
                PropertyIterator iter = sampler.getArguments().iterator();
                while (iter.hasNext()) {
                    Argument arg = (Argument) iter.next().getObjectValue();
                    modifyArgument(arg, url.getArguments());
                }
            } else {
                sampler.setArguments(url.getArguments());
                // config.parseArguments(url.getQueryString());
            }
            sampler.setProtocol(url.getProtocol());
        } else {
            log.debug("No matches found");
        }
    }