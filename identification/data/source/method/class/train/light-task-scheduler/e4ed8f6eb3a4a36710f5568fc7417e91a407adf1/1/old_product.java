private boolean sendReq(List<Node> monitorNodes, HttpCmd cmd) {
        boolean success = false;
        for (Node node : monitorNodes) {
            try {
                cmd.setNodeIdentity(node.getIdentity());
                HttpCmdResponse response = HttpCmdClient.doPost(node.getIp(), node.getPort(), cmd);
                if (response.isSuccess()) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Report Monitor Data Success.");
                    }
                    success = true;
                    break;
                } else {
                    LOGGER.warn("Report Monitor Data Failed: " + response.getMsg());
                }
            } catch (Exception e) {
                LOGGER.warn("Report Monitor Data Error: " + e.getMessage(), e);
                // 重试下一个
            }
        }
        return success;
    }