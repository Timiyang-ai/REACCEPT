@Ignore
    @Test
    public void testDeploy() {
        DeploymentFacility df = DeploymentFacilityFactory.getDeploymentFacility();
        ServerConnectionIdentifier sci = new ServerConnectionIdentifier();
        sci.setHostName("localhost");
        sci.setHostPort(4848); // 8080 for the REST client
        sci.setUserName("admin");
        sci.setPassword("adminadmin");
        
        df.connect(sci);
        
//        File archive = new File("C:\\tim\\asgroup\\dev-9p-fcs\\glassfish\\appserv-tests\\devtests\\deployment\\build\\servletonly.war");
        File archive = new File("/Users/Tim/asgroup/v3/warWithPlan/servletonly-portable.war");
        File plan = new File("/Users/Tim/asgroup/v3/warWithPlan/servletonly-deployplan.jar");
        DFDeploymentProperties options = new DFDeploymentProperties();
        options.setForce(true);
        options.setUpload(true);
        options.setName(APP_NAME);
        Properties props = new Properties();
        props.setProperty("keepSessions", "true");
        props.setProperty("foo", "bar");
        options.setProperties(props);
        DFProgressObject prog = df.deploy(
                new Target[0] /* ==> deploy to the default target */, 
                archive.toURI(), 
                plan.toURI(),
                options);
        DFDeploymentStatus ds = prog.waitFor();
        
        if (ds.getStatus() == DFDeploymentStatus.Status.FAILURE) {
            fail(ds.getAllStageMessages());
        }
        
    }