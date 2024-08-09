@Override
    public NetworkACLItem updateNetworkACLItem(NetworkACLItemVO networkACLItemVO) throws ResourceUnavailableException {
        networkACLItemVO.setState(State.Add);

        if (_networkACLItemDao.update(networkACLItemVO.getId(), networkACLItemVO)) {
            if (applyNetworkACL(networkACLItemVO.getAclId())) {
                return networkACLItemVO;
            } else {
                throw new CloudRuntimeException("Failed to apply Network ACL rule: " + networkACLItemVO.getUuid());
            }
        }
        throw new CloudRuntimeException(String.format("Network ACL rule [id=%s] acl rule list [id=%s] could not be updated.", networkACLItemVO.getUuid(), networkACLItemVO.getAclId()));
    }