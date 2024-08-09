@Test(description = "PUT /admin/network/{id}" )
   public void testUpdateNetwork() {
      //TODO: ensure network instanceof OrgNetwork, may require queries
      assertTrue(network instanceof OrgNetwork, String.format(URN_REQ_LIVE, "OrgNetwork"));
      
      OrgNetwork oldNetwork = Network.<OrgNetwork>toSubType(network)
            .toBuilder()
            .tasks(Collections.<Task>emptySet())
            .build();
      
      OrgNetwork updateNetwork = getMutatedOrgNetwork(oldNetwork);
      
      try {
         Task updateNetworkTask = networkApi.update(networkUrn, updateNetwork);
         Checks.checkTask(updateNetworkTask);
         assertTrue(retryTaskSuccess.apply(updateNetworkTask), String.format(TASK_COMPLETE_TIMELY, "updateNetworkTask"));
         network = networkApi.get(networkUrn);
         
         Checks.checkOrgNetwork(Network.<OrgNetwork>toSubType(network));
         
         assertTrue(equal(network.getConfiguration().getIpScope(), 
               updateNetwork.getConfiguration().getIpScope()), 
               String.format(OBJ_FIELD_UPDATABLE, NETWORK+".configuration", "ipScope"));
         assertTrue(equal(network.getConfiguration().getParentNetwork(), 
               updateNetwork.getConfiguration().getParentNetwork()), 
               String.format(OBJ_FIELD_UPDATABLE, NETWORK+".configuration", "parentNetwork"));
         assertTrue(equal(network.getConfiguration().getFenceMode(), 
               updateNetwork.getConfiguration().getFenceMode()), 
               String.format(OBJ_FIELD_UPDATABLE, NETWORK+".configuration", "fenceMode"));
         assertTrue(equal(network.getConfiguration().retainNetInfoAcrossDeployments(), 
               updateNetwork.getConfiguration().retainNetInfoAcrossDeployments()), 
               String.format(OBJ_FIELD_UPDATABLE, NETWORK+".configuration", "retainNetInfoAcrossDeployments"));
         assertTrue(equal(network.getConfiguration().getNetworkFeatures(), 
               updateNetwork.getConfiguration().getNetworkFeatures()), 
               String.format(OBJ_FIELD_UPDATABLE, NETWORK+".configuration", "networkFeatures"));
         assertTrue(equal(network.getConfiguration().getSyslogServerSettings(), 
               updateNetwork.getConfiguration().getSyslogServerSettings()), 
               String.format(OBJ_FIELD_UPDATABLE, NETWORK+".configuration", "syslogServerSettings"));
         assertTrue(equal(network.getConfiguration().getRouterInfo(), 
               updateNetwork.getConfiguration().getRouterInfo()), 
               String.format(OBJ_FIELD_UPDATABLE, NETWORK+".configuration", "routerInfo"));
         // FIXME: fails
//      assertTrue(equal(Network.<OrgNetwork>toSubType(network).getNetworkPool(), 
//            updateNetwork.getNetworkPool()), 
//            String.format(OBJ_FIELD_UPDATABLE, NETWORK, "networkPool"));
         
//      assertTrue(equal(Network.<OrgNetwork>toSubType(network).getAllowedExternalIpAddresses(), 
//            updateNetwork.getAllowedExternalIpAddresses()), 
//            String.format(OBJ_FIELD_UPDATABLE, NETWORK, "allowedExternalIpAddresses"));
      } finally {
         Task updateNetworkTask = networkApi.update(networkUrn, oldNetwork);
         Checks.checkTask(updateNetworkTask);
         assertTrue(retryTaskSuccess.apply(updateNetworkTask), String.format(TASK_COMPLETE_TIMELY, "updateNetworkTask"));
         network = networkApi.get(networkUrn);
      }
   }