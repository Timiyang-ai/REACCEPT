protected void transferDataToNetworkAclRulePojo(UpdateNetworkACLItemCmd updateNetworkACLItemCmd, NetworkACLItemVO networkACLItemVo, NetworkACL acl) {
        Integer number = updateNetworkACLItemCmd.getNumber();
        if (number != null) {
            NetworkACLItemVO aclNumber = _networkACLItemDao.findByAclAndNumber(acl.getId(), number);
            if (aclNumber != null && aclNumber.getId() != networkACLItemVo.getId()) {
                throw new InvalidParameterValueException("ACL item with number " + number + " already exists in ACL: " + acl.getUuid());
            }
            networkACLItemVo.setNumber(number);
        }

        Integer sourcePortStart = updateNetworkACLItemCmd.getSourcePortStart();
        if (sourcePortStart != null) {
            networkACLItemVo.setSourcePortStart(sourcePortStart);
        }
        Integer sourcePortEnd = updateNetworkACLItemCmd.getSourcePortEnd();
        if (sourcePortEnd != null) {
            networkACLItemVo.setSourcePortEnd(sourcePortEnd);
        }
        List<String> sourceCidrList = updateNetworkACLItemCmd.getSourceCidrList();
        if (CollectionUtils.isNotEmpty(sourceCidrList)) {
            networkACLItemVo.setSourceCidrList(sourceCidrList);
        }
        String protocol = updateNetworkACLItemCmd.getProtocol();
        if (StringUtils.isNotBlank(protocol)) {
            networkACLItemVo.setProtocol(protocol);
        }
        Integer icmpCode = updateNetworkACLItemCmd.getIcmpCode();
        if (icmpCode != null) {
            networkACLItemVo.setIcmpCode(icmpCode);
        }
        Integer icmpType = updateNetworkACLItemCmd.getIcmpType();
        if (icmpType != null) {
            networkACLItemVo.setIcmpType(icmpType);
        }
        String action = updateNetworkACLItemCmd.getAction();
        if (StringUtils.isNotBlank(action)) {
            Action aclRuleAction = validateAndCreateNetworkAclRuleAction(action);
            networkACLItemVo.setAction(aclRuleAction);
        }
        TrafficType trafficType = updateNetworkACLItemCmd.getTrafficType();
        if (trafficType != null) {
            networkACLItemVo.setTrafficType(trafficType);
        }
        String customId = updateNetworkACLItemCmd.getCustomId();
        if (StringUtils.isNotBlank(customId)) {
            networkACLItemVo.setUuid(customId);
        }
        boolean display = updateNetworkACLItemCmd.isDisplay();
        if (display != networkACLItemVo.isDisplay()) {
            networkACLItemVo.setDisplay(display);
        }
        String reason = updateNetworkACLItemCmd.getReason();
        if (StringUtils.isNotBlank(reason)) {
            networkACLItemVo.setReason(reason);
        }
    }