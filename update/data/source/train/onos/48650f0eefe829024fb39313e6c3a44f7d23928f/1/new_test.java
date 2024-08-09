@Ignore
    @Test
    public void testGetConnectedClient() {
        OpenstackNode.Builder osNodeBuilderV2 = DefaultOpenstackNode.builder();
        OpenstackAuth.Builder osNodeAuthBuilderV2 = DefaultOpenstackAuth.builder()
                .version("v2.0")
                .protocol(OpenstackAuth.Protocol.HTTP)
                .project("admin")
                .username("admin")
                .password("password")
                .perspective(OpenstackAuth.Perspective.PUBLIC);

        String endpointV2 = "1.1.1.1:35357/v2.0";

        KeystoneConfig keystoneConfigV2 = DefaultKeystoneConfig.builder()
                .authentication(osNodeAuthBuilderV2.build())
                .endpoint(endpointV2)
                .build();

        openstackControlNodeV2 = osNodeBuilderV2.hostname("controllerv2")
                .type(OpenstackNode.NodeType.CONTROLLER)
                .managementIp(IpAddress.valueOf("1.1.1.1"))
                .keystoneConfig(keystoneConfigV2)
                .state(NodeState.COMPLETE)
                .build();

        OpenstackNode.Builder osNodeBuilderV3 = DefaultOpenstackNode.builder();
        OpenstackAuth.Builder osNodeAuthBuilderV3 = DefaultOpenstackAuth.builder()
                .version("v2")
                .protocol(OpenstackAuth.Protocol.HTTP)
                .project("admin")
                .username("admin")
                .password("password")
                .perspective(OpenstackAuth.Perspective.PUBLIC);

        String endpointV3 = "2.2.2.2:80/v3";

        KeystoneConfig keystoneConfigV3 = DefaultKeystoneConfig.builder()
                .authentication(osNodeAuthBuilderV3.build())
                .endpoint(endpointV3)
                .build();

        openstackControlNodeV3 = osNodeBuilderV3.hostname("controllerv3")
                .type(OpenstackNode.NodeType.CONTROLLER)
                .managementIp(IpAddress.valueOf("2.2.2.2"))
                .keystoneConfig(keystoneConfigV3)
                .state(NodeState.COMPLETE)
                .build();

        getConnectedClient(openstackControlNodeV2);
        getConnectedClient(openstackControlNodeV3);
    }