@Override
    public NetworkACLItem createNetworkACLItem(CreateNetworkACLCmd createNetworkACLCmd) {
        Long aclId = createAclListIfNeeded(createNetworkACLCmd);

        Integer sourcePortStart = createNetworkACLCmd.getSourcePortStart();
        Integer sourcePortEnd = createNetworkACLCmd.getSourcePortEnd();
        String protocol = createNetworkACLCmd.getProtocol();
        List<String> sourceCidrList = createNetworkACLCmd.getSourceCidrList();
        Integer icmpCode = createNetworkACLCmd.getIcmpCode();
        Integer icmpType = createNetworkACLCmd.getIcmpType();
        TrafficType trafficType = createNetworkACLCmd.getTrafficType();
        String reason = createNetworkACLCmd.getReason();
        String action = createNetworkACLCmd.getAction();

        NetworkACL acl = _networkAclMgr.getNetworkACL(aclId);

        validateNetworkAcl(acl);
        validateAclRuleNumber(createNetworkACLCmd, acl);

        NetworkACLItem.Action ruleAction = validateAndCreateNetworkAclRuleAction(action);
        Integer number = createNetworkACLCmd.getNumber();
        if (number == null) {
            number = _networkACLItemDao.getMaxNumberByACL(aclId) + 1;
        }
        NetworkACLItemVO networkACLItemVO = new NetworkACLItemVO(sourcePortStart, sourcePortEnd, protocol, aclId, sourceCidrList, icmpCode, icmpType, trafficType, ruleAction, number, reason);
        networkACLItemVO.setDisplay(createNetworkACLCmd.isDisplay());

        validateNetworkACLItem(networkACLItemVO);
        return _networkAclMgr.createNetworkACLItem(networkACLItemVO);
    }