public void deleteNode(String nodeId) throws NoResponseException, XMPPErrorException
	{
		sendPubsubPacket(Type.SET, new NodeExtension(PubSubElementType.DELETE, nodeId), PubSubElementType.DELETE.getNamespace());
		nodeMap.remove(nodeId);
	}