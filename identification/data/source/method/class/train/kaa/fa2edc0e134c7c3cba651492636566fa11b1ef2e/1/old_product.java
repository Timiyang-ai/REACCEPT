public void recalculate() {
        LOG.info("DynamicLoadManager recalculate() started... lastBootstrapServersUpdateFailed {}", lastBootstrapServersUpdateFailed);
        if (lastBootstrapServersUpdateFailed) {
            LOG.trace("Registred {} Bootstrap servers", bootstrapsMap.size());
            lastBootstrapServersUpdateFailed = false;
            for (BootstrapNodeInfo bootstrapNodeInfo : bootstrapsMap.values()) {
                updateBootstrap(bootstrapNodeInfo);
            }
        }
        if (dynamicMgmt != null) {
            Map<String, OperationsServerLoadHistory> opsServerHistory = new HashMap<String, OperationsServerLoadHistory>();
            for (String dnsName : opsServersMap.keySet()) {
                opsServerHistory.put(dnsName, opsServersMap.get(dnsName).history);
            }
            Map<String, RedirectionRule> rules = dynamicMgmt.recalculate(opsServerHistory);
            LOG.trace("DynamicLoadManager recalculate() got {} redirection rules", rules.size());
            for (String dnsName : rules.keySet()) {
                if (opsServersMap.containsKey(dnsName)) {
                    sendRedirectionRule(dnsName, opsServersMap.get(dnsName).nodeInfo, rules.get(dnsName));
                } else {
                    LOG.error("Operations server {} redirection rule exist, but NO server available, skip setting rule.", dnsName);
                }
            }
        }
    }