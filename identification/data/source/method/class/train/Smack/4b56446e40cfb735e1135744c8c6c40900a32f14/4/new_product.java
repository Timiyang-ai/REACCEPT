public void deleteNode(String nodeId) throws NoResponseException, XMPPErrorException, NotConnectedException, InterruptedException
	{
		sendPubsubPacket(Type.set, new NodeExtension(PubSubElementType.DELETE, nodeId), PubSubElementType.DELETE.getNamespace());
		nodeMap.remove(nodeId);
	}