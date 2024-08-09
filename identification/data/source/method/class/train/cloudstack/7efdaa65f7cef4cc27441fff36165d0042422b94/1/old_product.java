@Override
    public NetworkACLItem createNetworkACLItem(final CreateNetworkACLCmd aclItemCmd) {
        final Account caller = CallContext.current().getCallingAccount();
        Long aclId = aclItemCmd.getACLId();
        if (aclId == null) {
            //ACL id is not specified. Get the ACL details from network
            if (aclItemCmd.getNetworkId() == null) {
                throw new InvalidParameterValueException("Cannot create Network ACL Item. ACL Id or network Id is required");
            }
            final Network network = _networkMgr.getNetwork(aclItemCmd.getNetworkId());
            if (network.getVpcId() == null) {
                throw new InvalidParameterValueException("Network: " + network.getUuid() + " does not belong to VPC");
            }
            aclId = network.getNetworkACLId();

            if (aclId == null) {
                //Network is not associated with any ACL. Create a new ACL and add aclItem in it for backward compatibility
                s_logger.debug("Network " + network.getId() + " is not associated with any ACL. Creating an ACL before adding acl item");

                //verify that ACLProvider is supported by network offering
                if (!_networkModel.areServicesSupportedByNetworkOffering(network.getNetworkOfferingId(), Network.Service.NetworkACL)) {
                    throw new InvalidParameterValueException("Network Offering does not support NetworkACL service");
                }

                final Vpc vpc = _entityMgr.findById(Vpc.class, network.getVpcId());
                if (vpc == null) {
                    throw new InvalidParameterValueException("Unable to find Vpc associated with the Network");
                }

                //Create new ACL
                final String aclName = "VPC_" + vpc.getName() + "_Tier_" + network.getName() + "_ACL_" + network.getUuid();
                final String description = "ACL for " + aclName;
                final NetworkACL acl = _networkAclMgr.createNetworkACL(aclName, description, network.getVpcId(), aclItemCmd.getDisplay());
                if (acl == null) {
                    throw new CloudRuntimeException("Error while create ACL before adding ACL Item for network " + network.getId());
                }
                s_logger.debug("Created ACL: " + aclName + " for network " + network.getId());
                aclId = acl.getId();
                //Apply acl to network
                try {
                    if (!_networkAclMgr.replaceNetworkACL(acl, (NetworkVO)network)) {
                        throw new CloudRuntimeException("Unable to apply auto created ACL to network " + network.getId());
                    }
                    s_logger.debug("Created ACL is applied to network " + network.getId());
                } catch (final ResourceUnavailableException e) {
                    throw new CloudRuntimeException("Unable to apply auto created ACL to network " + network.getId(), e);
                }
            }
        }

        final NetworkACL acl = _networkAclMgr.getNetworkACL(aclId);
        if (acl == null) {
            throw new InvalidParameterValueException("Unable to find specified ACL");
        }

        if (aclId == NetworkACL.DEFAULT_DENY || aclId == NetworkACL.DEFAULT_ALLOW) {
            throw new InvalidParameterValueException("Default ACL cannot be modified");
        }

        final Vpc vpc = _entityMgr.findById(Vpc.class, acl.getVpcId());
        if (vpc == null) {
            throw new InvalidParameterValueException("Unable to find Vpc associated with the NetworkACL");
        }
        _accountMgr.checkAccess(caller, null, true, vpc);

        //Ensure that number is unique within the ACL
        if (aclItemCmd.getNumber() != null) {
            if (_networkACLItemDao.findByAclAndNumber(aclId, aclItemCmd.getNumber()) != null) {
                throw new InvalidParameterValueException("ACL item with number " + aclItemCmd.getNumber() + " already exists in ACL: " + acl.getUuid());
            }
        }

        validateNetworkACLItem(aclItemCmd.getSourcePortStart(), aclItemCmd.getSourcePortEnd(), aclItemCmd.getSourceCidrList(), aclItemCmd.getProtocol(),
                aclItemCmd.getIcmpCode(), aclItemCmd.getIcmpType(), aclItemCmd.getAction(), aclItemCmd.getNumber());

        return _networkAclMgr.createNetworkACLItem(aclItemCmd.getSourcePortStart(), aclItemCmd.getSourcePortEnd(), aclItemCmd.getProtocol(),
                aclItemCmd.getSourceCidrList(), aclItemCmd.getIcmpCode(), aclItemCmd.getIcmpType(), aclItemCmd.getTrafficType(), aclId, aclItemCmd.getAction(),
                aclItemCmd.getNumber(), aclItemCmd.getDisplay());
    }