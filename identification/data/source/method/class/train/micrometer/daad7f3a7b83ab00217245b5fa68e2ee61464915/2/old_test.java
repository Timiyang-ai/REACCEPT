    @Test
    void writeGauge() {
        writeGauge(this.meterNameEventTypeEnabledRegistry, "{\"eventType\":\"myGauge\",\"value\":1}");
        writeGauge(this.registry,
                "{\"eventType\":\"MicrometerSample\",\"value\":1,\"metricName\":\"myGauge\",\"metricType\":\"GAUGE\"}");
    }