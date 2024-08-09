void onNodeMetrics(UUID nodeId, Object msg, byte plc) {
        if (msg instanceof MetricResponse) {
            MetricResponse res = (MetricResponse)msg;

            // TODO GG-22191 change on debug level.
            log.info("Send message to GMC: " + msg);

            try {
                // TODO: workaround of spring-messaging bug with send byte array data.
                // https://github.com/spring-projects/spring-framework/issues/23358
                StompHeaders headers = new StompHeaders();
                headers.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM);
                headers.setDestination(buildMetricsDest(res.clusterId()));

                mgr.send(headers, res.body());
            }
            catch (Throwable e) {
                log.error("Failed to send metrics to GMC", e);
            }
        }
    }