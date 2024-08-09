public void deleteNode(String nodeId)
		throws XMPPException
	{
		sendPubsubPacket(Type.SET, new NodeExtension(PubSubElementType.DELETE, nodeId), PubSubElementType.DELETE.getNamespace());
		nodeMap.remove(nodeId);
	}