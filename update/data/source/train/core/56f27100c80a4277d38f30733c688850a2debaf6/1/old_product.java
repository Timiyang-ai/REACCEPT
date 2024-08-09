public Weld initialize()
   {

      SEWeldDeployment deployment = new SEWeldDeployment()
      {
      };
      bootstrap.startContainer(Environments.SE, deployment, this.applicationBeanStore);
      final BeanDeploymentArchive mainBeanDepArch = deployment.getBeanDeploymentArchives().get(0);
      this.manager = bootstrap.getManager(mainBeanDepArch);
      bootstrap.startInitialization();
      bootstrap.deployBeans();
      WeldManagerUtils.getInstanceByType(manager, ShutdownManager.class).setBootstrap(bootstrap);
      bootstrap.validateBeans();
      bootstrap.endInitialization();

      instanceManager = WeldManagerUtils.getInstanceByType(manager, InstanceManager.class);

      return this;
   }