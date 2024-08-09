protected void transferDataToNetworkAclRulePojo(UpdateNetworkACLItemCmd updateNetworkACLItemCmd, NetworkACLItemVO networkACLItemVo, NetworkACL acl) {
        Integer number = updateNetworkACLItemCmd.getNumber();
        if (number != null) {
            NetworkACLItemVO aclNumber = _networkACLItemDao.findByAclAndNumber(acl.getId(), number);
            if (aclNumber != null && aclNumber.getId() != networkACLItemVo.getId()) {
                throw new InvalidParameterValueException("ACL item with number " + number + " already exists in ACL: " + acl.getUuid());
            }
            networkACLItemVo.setNumber(number);
        }
        boolean isPartialUpgrade = updateNetworkACLItemCmd.isPartialUpgrade();

        Integer sourcePortStart = updateNetworkACLItemCmd.getSourcePortStart();
        if (!isPartialUpgrade || sourcePortStart != null) {
            networkACLItemVo.setSourcePortStart(sourcePortStart);
        }
        Integer sourcePortEnd = updateNetworkACLItemCmd.getSourcePortEnd();
        if (!isPartialUpgrade || sourcePortEnd != null) {
            networkACLItemVo.setSourcePortEnd(sourcePortEnd);
        }
        List<String> sourceCidrList = updateNetworkACLItemCmd.getSourceCidrList();
        if (!isPartialUpgrade || CollectionUtils.isNotEmpty(sourceCidrList)) {
            networkACLItemVo.setSourceCidrList(sourceCidrList);
        }
        String protocol = updateNetworkACLItemCmd.getProtocol();
        if (!isPartialUpgrade || StringUtils.isNotBlank(protocol)) {
            networkACLItemVo.setProtocol(protocol);
        }
        Integer icmpCode = updateNetworkACLItemCmd.getIcmpCode();
        if (!isPartialUpgrade || icmpCode != null) {
            networkACLItemVo.setIcmpCode(icmpCode);
        }
        Integer icmpType = updateNetworkACLItemCmd.getIcmpType();
        if (!isPartialUpgrade || icmpType != null) {
            networkACLItemVo.setIcmpType(icmpType);
        }
        String action = updateNetworkACLItemCmd.getAction();
        if (!isPartialUpgrade || StringUtils.isNotBlank(action)) {
            Action aclRuleAction = validateAndCreateNetworkAclRuleAction(action);
            networkACLItemVo.setAction(aclRuleAction);
        }
        TrafficType trafficType = updateNetworkACLItemCmd.getTrafficType();
        if (!isPartialUpgrade || trafficType != null) {
            networkACLItemVo.setTrafficType(trafficType);
        }
        String customId = updateNetworkACLItemCmd.getCustomId();
        if (StringUtils.isNotBlank(customId)) {
            networkACLItemVo.setUuid(customId);
        }
        boolean display = updateNetworkACLItemCmd.isDisplay();
        if (!isPartialUpgrade || display != networkACLItemVo.isDisplay()) {
            networkACLItemVo.setDisplay(display);
        }
        String reason = updateNetworkACLItemCmd.getReason();
        if (!isPartialUpgrade || StringUtils.isNotBlank(reason)) {
            networkACLItemVo.setReason(reason);
        }
    }