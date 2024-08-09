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

        openstackControlNodeV2 = osNodeBuilderV2.hostname("controllerv2")
                .type(OpenstackNode.NodeType.CONTROLLER)
                .managementIp(IpAddress.valueOf("1.1.1.1"))
                .endpoint("1.1.1.1")
                .authentication(osNodeAuthBuilderV2.build())
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

        openstackControlNodeV3 = osNodeBuilderV3.hostname("controllerv3")
                .type(OpenstackNode.NodeType.CONTROLLER)
                .managementIp(IpAddress.valueOf("2.2.2.2"))
                .endpoint("2.2.2.2")
                .authentication(osNodeAuthBuilderV3.build())
                .state(NodeState.COMPLETE)
                .build();

        getConnectedClient(openstackControlNodeV2);
        getConnectedClient(openstackControlNodeV3);

    }