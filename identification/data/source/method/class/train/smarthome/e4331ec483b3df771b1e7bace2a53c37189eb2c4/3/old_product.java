protected MqttBrokerConnection createBrokerConnection() throws IllegalArgumentException {
        String host = config.host;
        if (StringUtils.isBlank(host) || host == null) {
            throw new IllegalArgumentException("Host is empty!");
        }
        final MqttBrokerConnection connection = new MqttBrokerConnection(host, config.port, false, config.clientID);

        final String username = config.username;
        final String password = config.password;
        if (StringUtils.isNotBlank(username) && password != null) {
            connection.setCredentials(username, password); // Empty passwords are allowed
        }

        final String topic = config.lwtTopic;
        if (topic != null) {
            final String msg = config.lwtMessage;
            MqttWillAndTestament will = new MqttWillAndTestament(topic, msg != null ? msg.getBytes() : null,
                    config.lwtQos, config.lwtRetain);
            connection.setLastWill(will);
        }

        connection.setQos(config.qos);
        if (config.reconnectTime != null) {
            connection.setReconnectStrategy(new PeriodicReconnectStrategy(config.reconnectTime, 10000));
        }
        final Integer keepAlive = config.keepAlive;
        if (keepAlive != null) {
            connection.setKeepAliveInterval(keepAlive);
        }
        if (config.timeoutInMs != null) {
            connection.setTimeoutExecutor(scheduler, TIMEOUT_DEFAULT);
        }

        connection.setRetain(config.retainMessages);

        return connection;
    }