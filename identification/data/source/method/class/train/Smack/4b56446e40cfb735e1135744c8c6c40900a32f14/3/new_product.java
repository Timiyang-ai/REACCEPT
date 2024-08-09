public boolean deleteNode(String nodeId) throws NoResponseException, XMPPErrorException, NotConnectedException, InterruptedException {
        boolean res = true;
        try {
            sendPubsubPacket(Type.set, new NodeExtension(PubSubElementType.DELETE, nodeId), PubSubElementType.DELETE.getNamespace());
        } catch (XMPPErrorException e) {
            if (e.getStanzaError().getCondition() == StanzaError.Condition.item_not_found) {
                res = false;
            } else {
                throw e;
            }
        }
        nodeMap.remove(nodeId);
        return res;
    }