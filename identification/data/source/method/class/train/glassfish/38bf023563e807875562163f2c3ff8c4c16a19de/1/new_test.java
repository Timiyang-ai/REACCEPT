@Ignore 
    @Test
    public void testDeploy() {
        DeploymentFacility df = DeploymentFacilityFactory.getDeploymentFacility();
        ServerConnectionIdentifier sci = new ServerConnectionIdentifier();
        sci.setHostName("localhost");
        sci.setHostPort(4848); // 8080 for the REST client
        sci.setUserName("admin");
        sci.setPassword("");
        
        df.connect(sci);
        
        File archive = new File("/home/hzhang/deployment/apps/jsr88/servletonly-portable.war");
        File plan = new File("/home/hzhang/deployment/apps/jsr88/servletonly-deployplan.jar");
        DFDeploymentProperties options = new DFDeploymentProperties();
        options.setForce(true);
        options.setUpload(true);
        options.setName(APP_NAME);
        Properties props = new Properties();
        props.setProperty("keepSessions", "true");
        props.setProperty("foo", "bar");
        options.setProperties(props);

        try {
        Target[] targets = df.listTargets(); 
        DFProgressObject prog = df.deploy(
                targets /* ==> deploy to the default target */, 
                archive.toURI(), 
                plan.toURI(),
                options);
        DFDeploymentStatus ds = prog.waitFor();
        
        if (ds.getStatus() == DFDeploymentStatus.Status.FAILURE) {
            fail(ds.getAllStageMessages());
        }
        } catch (Exception e) {
          e.printStackTrace();
          fail(e.getMessage());
        }
        
    }