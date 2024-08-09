protected MqttBrokerConnection createBrokerConnection() throws IllegalArgumentException {
        String host = config.host;
        if (StringUtils.isBlank(host) || host == null) {
            throw new IllegalArgumentException("Host is empty!");
        }
        MqttBrokerConnection c = new MqttBrokerConnection(host, config.port, false, config.clientID);

        String username = config.username;
        String password = config.password;
        if (StringUtils.isNotBlank(username) && password != null) {
            c.setCredentials(username, password); // Empty passwords are allowed
        }

        if (config.lwtTopic != null) {
            String topic = config.lwtTopic;
            MqttWillAndTestament will = new MqttWillAndTestament(topic,
                    config.lwtMessage != null ? config.lwtMessage.getBytes() : null, config.lwtQos, config.lwtRetain);
            logger.debug("Setting last will: {}", will);
            c.setLastWill(will);
        }

        c.setQos(config.qos);
        if (config.reconnectTime != null) {
            c.setReconnectStrategy(new PeriodicReconnectStrategy(config.reconnectTime, 10000));
        }
        if (config.keepAlive != null) {
            c.setKeepAliveInterval(config.keepAlive);
        }
        if (config.timeoutInMs != null) {
            c.setTimeoutExecutor(scheduler, TIMEOUT_DEFAULT);
        }

        c.setRetain(config.retainMessages);

        return c;
    }