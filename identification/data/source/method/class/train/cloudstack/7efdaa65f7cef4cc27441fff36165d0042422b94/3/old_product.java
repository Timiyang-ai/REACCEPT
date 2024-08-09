@Override
    public NetworkACLItem updateNetworkACLItem(final Long id, final String protocol, final List<String> sourceCidrList, final NetworkACLItem.TrafficType trafficType, final String action,
            final Integer number, final Integer sourcePortStart, final Integer sourcePortEnd, final Integer icmpCode, final Integer icmpType, final String customId, final Boolean forDisplay) throws ResourceUnavailableException {
        final NetworkACLItemVO aclItem = _networkACLItemDao.findById(id);
        aclItem.setState(State.Add);

        if (protocol != null) {
            aclItem.setProtocol(protocol);
        }

        if (sourceCidrList != null) {
            aclItem.setSourceCidrList(sourceCidrList);
        }

        if (trafficType != null) {
            aclItem.setTrafficType(trafficType);
        }

        if (action != null) {
            NetworkACLItem.Action ruleAction = NetworkACLItem.Action.Allow;
            if ("deny".equalsIgnoreCase(action)) {
                ruleAction = NetworkACLItem.Action.Deny;
            }
            aclItem.setAction(ruleAction);
        }

        if (number != null) {
            aclItem.setNumber(number);
        }

        if (sourcePortStart != null) {
            aclItem.setSourcePortStart(sourcePortStart);
        }

        if (sourcePortEnd != null) {
            aclItem.setSourcePortEnd(sourcePortEnd);
        }

        if (icmpCode != null) {
            aclItem.setIcmpCode(icmpCode);
        }

        if (icmpType != null) {
            aclItem.setIcmpType(icmpType);
        }

        if (customId != null) {
            aclItem.setUuid(customId);
        }

        if (forDisplay != null) {
            aclItem.setDisplay(forDisplay);
        }

        if (_networkACLItemDao.update(id, aclItem)) {
            if (applyNetworkACL(aclItem.getAclId())) {
                return aclItem;
            } else {
                throw new CloudRuntimeException("Failed to apply Network ACL Item: " + aclItem.getUuid());
            }
        }
        return null;
    }