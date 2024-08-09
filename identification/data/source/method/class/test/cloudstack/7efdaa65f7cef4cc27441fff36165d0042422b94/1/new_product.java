protected void validateNetworkACLItem(NetworkACLItemVO networkACLItemVO) {
        validateSourceStartAndEndPorts(networkACLItemVO);
        validateSourceCidrList(networkACLItemVO);
        validateProtocol(networkACLItemVO);
    }