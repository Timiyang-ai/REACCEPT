@Override
    public NetworkACLItem updateNetworkACLItem(final Long id, final String protocol, final List<String> sourceCidrList, final NetworkACLItem.TrafficType trafficType, final String action,
            final Integer number, final Integer sourcePortStart, final Integer sourcePortEnd, final Integer icmpCode, final Integer icmpType, final String newUUID, final Boolean forDisplay) throws ResourceUnavailableException {
        final NetworkACLItemVO aclItem = _networkACLItemDao.findById(id);
        if (aclItem == null) {
            throw new InvalidParameterValueException("Unable to find ACL Item cannot be found");
        }

        if (aclItem.getAclId() == NetworkACL.DEFAULT_ALLOW || aclItem.getAclId() == NetworkACL.DEFAULT_DENY) {
            throw new InvalidParameterValueException("Default ACL Items cannot be updated");
        }

        final NetworkACL acl = _networkAclMgr.getNetworkACL(aclItem.getAclId());

        final Vpc vpc = _entityMgr.findById(Vpc.class, acl.getVpcId());

        final Account caller = CallContext.current().getCallingAccount();

        _accountMgr.checkAccess(caller, null, true, vpc);

        if (number != null) {
            //Check if ACL Item with specified number already exists
            final NetworkACLItemVO aclNumber = _networkACLItemDao.findByAclAndNumber(acl.getId(), number);
            if (aclNumber != null && aclNumber.getId() != id) {
                throw new InvalidParameterValueException("ACL item with number " + number + " already exists in ACL: " + acl.getUuid());
            }
        }

        validateNetworkACLItem(sourcePortStart == null ? aclItem.getSourcePortStart() : sourcePortStart, sourcePortEnd == null ? aclItem.getSourcePortEnd()
                : sourcePortEnd, sourceCidrList, protocol, icmpCode, icmpType == null ? aclItem.getIcmpType() : icmpType, action, number);

        return _networkAclMgr.updateNetworkACLItem(id, protocol, sourceCidrList, trafficType, action, number, sourcePortStart, sourcePortEnd, icmpCode, icmpType, newUUID, forDisplay);
    }