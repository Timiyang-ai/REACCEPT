@Override
    public NetworkACLItem updateNetworkACLItem(UpdateNetworkACLItemCmd updateNetworkACLItemCmd) throws ResourceUnavailableException {
        NetworkACLItemVO networkACLItemVo = validateNetworkAclRuleIdAndRetrieveIt(updateNetworkACLItemCmd);

        NetworkACL acl = _networkAclMgr.getNetworkACL(networkACLItemVo.getAclId());
        validateNetworkAcl(acl);

        transferDataToNetworkAclRulePojo(updateNetworkACLItemCmd, networkACLItemVo, acl);
        validateNetworkACLItem(networkACLItemVo);
        return _networkAclMgr.updateNetworkACLItem(networkACLItemVo);
    }