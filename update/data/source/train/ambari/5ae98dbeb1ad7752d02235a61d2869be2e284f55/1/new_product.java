private void processServiceComponentHosts(Cluster cluster, KerberosDescriptor kerberosDescriptor,
                                            Map<String, Map<String, String>> currentConfigurations,
                                            Map<String, Map<String, String>> kerberosConfigurations,
                                            Map<String, Set<String>> propertiesToBeIgnored)
      throws AmbariException {

    Collection<Host> hosts = cluster.getHosts();
    if (!hosts.isEmpty()) {
      // Create the context to use for filtering Kerberos Identities based on the state of the cluster
      Map<String, Object> filterContext = new HashMap<>();
      filterContext.put("configurations", currentConfigurations);
      filterContext.put("services", cluster.getServices().keySet());

      try {
        Map<String, Set<String>> propertiesToIgnore = null;

        for (Host host : hosts) {
          // Iterate over the components installed on the current host to get the service and
          // component-level Kerberos descriptors in order to determine which principals,
          // keytab files, and configurations need to be created or updated.
          for (ServiceComponentHost sch : cluster.getServiceComponentHosts(host.getHostName())) {
            String hostName = sch.getHostName();

            String serviceName = sch.getServiceName();
            String componentName = sch.getServiceComponentName();

            KerberosServiceDescriptor serviceDescriptor = kerberosDescriptor.getService(serviceName);

            if (serviceDescriptor != null) {
              List<KerberosIdentityDescriptor> serviceIdentities = serviceDescriptor.getIdentities(true, filterContext);

              // Add service-level principals (and keytabs)
              kerberosHelper.addIdentities(null, serviceIdentities,
                  null, hostName, serviceName, componentName, kerberosConfigurations, currentConfigurations, false);
              propertiesToIgnore = gatherPropertiesToIgnore(serviceIdentities, propertiesToIgnore);

              KerberosComponentDescriptor componentDescriptor = serviceDescriptor.getComponent(componentName);

              if (componentDescriptor != null) {
                List<KerberosIdentityDescriptor> componentIdentities = componentDescriptor.getIdentities(true, filterContext);

                // Calculate the set of configurations to update and replace any variables
                // using the previously calculated Map of configurations for the host.
                kerberosHelper.mergeConfigurations(kerberosConfigurations,
                    componentDescriptor.getConfigurations(true), currentConfigurations, null);

                // Add component-level principals (and keytabs)
                kerberosHelper.addIdentities(null, componentIdentities,
                    null, hostName, serviceName, componentName, kerberosConfigurations, currentConfigurations, false);
                propertiesToIgnore = gatherPropertiesToIgnore(componentIdentities, propertiesToIgnore);
              }
            }
          }
        }

        // Add ambari-server identities only if 'kerberos-env.create_ambari_principal = true'
        if (kerberosHelper.createAmbariIdentities(currentConfigurations.get(KERBEROS_ENV))) {
          List<KerberosIdentityDescriptor> ambariIdentities = kerberosHelper.getAmbariServerIdentities(kerberosDescriptor);

          for (KerberosIdentityDescriptor identity : ambariIdentities) {
            // If the identity represents the ambari-server user, use the component name "AMBARI_SERVER_SELF"
            // so it can be distinguished between other identities related to the AMBARI-SERVER
            // component.
            String componentName = KerberosHelper.AMBARI_SERVER_KERBEROS_IDENTITY_NAME.equals(identity.getName())
                ? "AMBARI_SERVER_SELF"
                : "AMBARI_SERVER";

            List<KerberosIdentityDescriptor> componentIdentities = Collections.singletonList(identity);
            kerberosHelper.addIdentities(null, componentIdentities,
                null, KerberosHelper.AMBARI_SERVER_HOST_NAME, "AMBARI", componentName, kerberosConfigurations, currentConfigurations, false);
            propertiesToIgnore = gatherPropertiesToIgnore(componentIdentities, propertiesToIgnore);
          }
        }

        if ((propertiesToBeIgnored != null) && (propertiesToIgnore != null)) {
          propertiesToBeIgnored.putAll(propertiesToIgnore);
        }
      } catch (IOException e) {
        throw new AmbariException(e.getMessage(), e);
      }
    }
  }