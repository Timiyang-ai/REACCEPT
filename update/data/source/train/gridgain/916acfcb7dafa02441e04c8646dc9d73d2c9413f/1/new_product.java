void onNodeMetrics(UUID nodeId, Object msg, byte plc) {
        if (msg instanceof MetricResponse) {
            MetricResponse res = (MetricResponse)msg;

            // TODO GG-22191 change on debug level.
            log.info("Send message to GMC: " + msg);

            try {
                mgr.send(buildMetricsDest(res.clusterId()), res.body());
            }
            catch (Throwable e) {
                log.error("Failed to send metrics to GMC", e);
            }
        }
    }