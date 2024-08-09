private boolean sendReq(List<Node> monitorNodes, HttpCmd cmd) {
        while (true) {
            Node node = selectMNode(monitorNodes);
            try {
                cmd.setNodeIdentity(node.getIdentity());
                HttpCmdResponse response = HttpCmdClient.doPost(node.getIp(), node.getPort(), cmd);
                if (response.isSuccess()) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Report Monitor Data Success.");
                    }
                    return true;
                } else {
                    LOGGER.warn("Report Monitor Data Failed: " + response.getMsg());
                    monitorNodes.remove(node);
                }
            } catch (Exception e) {
                LOGGER.warn("Report Monitor Data Error: " + e.getMessage(), e);
                // 重试下一个
            }
            if (monitorNodes.size() == 0) {
                return false;
            }
        }
    }