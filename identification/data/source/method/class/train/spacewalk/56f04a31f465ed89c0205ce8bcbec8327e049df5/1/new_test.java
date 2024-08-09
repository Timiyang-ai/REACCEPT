    public static Action createAction(User usr, ActionType type) throws Exception {
        Action newA = ActionFactory.createAction(type);
        Long orgId = usr.getOrg().getId();
        newA.setSchedulerUser(usr);
        if (type.equals(ActionFactory.TYPE_ERRATA)) {
            Errata e1 = ErrataFactoryTest.createTestErrata(orgId);
            Errata e2 = ErrataFactoryTest.createTestErrata(orgId);
            // add the errata
            ((ErrataAction) newA).addErrata(e1);
            ((ErrataAction) newA).addErrata(e2);
        }
        else if (type.equals(ActionFactory.TYPE_CONFIGFILES_MTIME_UPLOAD)) {
            ConfigUploadMtimeAction cua = (ConfigUploadMtimeAction) newA;
            ConfigDateFileAction cfda = new ConfigDateFileAction();
            cfda.setFileName("/tmp/rhn-java-" + TestUtils.randomString());
            cfda.setFileType("W");
            cfda.setCreated(new Date());
            cfda.setModified(new Date());
            cua.addConfigDateFileAction(cfda);

            Server newS = ServerFactoryTest.createTestServer(usr);
            ConfigRevision cr = ConfigTestUtils.createConfigRevision(usr.getOrg());
            cua.addConfigChannelAndServer(cr.getConfigFile().getConfigChannel(), newS);
            // rhnActionConfigChannel requires a ServerAction to exist
            cua.addServerAction(ServerActionTest.createServerAction(newS, newA));
            ConfigDateDetails cdd = new ConfigDateDetails();
            cdd.setCreated(new Date());
            cdd.setModified(new Date());
            cdd.setStartDate(new Date());
            cdd.setImportContents("Y");
            cdd.setParentAction(cua);
            cua.setConfigDateDetails(cdd);
        }
        else if (type.equals(ActionFactory.TYPE_CONFIGFILES_UPLOAD)) {
            ConfigUploadAction cua = (ConfigUploadAction) newA;
            Server newS = ServerFactoryTest.createTestServer(usr);

            ConfigRevision cr = ConfigTestUtils.createConfigRevision(usr.getOrg());
            cua.addConfigChannelAndServer(cr.getConfigFile().getConfigChannel(), newS);
            cua.addServerAction(ServerActionTest.createServerAction(newS, newA));

            ConfigFileName name1 =
                ConfigurationFactory.lookupOrInsertConfigFileName("/etc/foo");
            ConfigFileName name2 =
                ConfigurationFactory.lookupOrInsertConfigFileName("/etc/bar");
            cua.addConfigFileName(name1, newS);
            cua.addConfigFileName(name2, newS);
        }
        else if (type.equals(ActionFactory.TYPE_CONFIGFILES_DEPLOY)) {
            Server newS = ServerFactoryTest.createTestServer(usr, true);
            ConfigRevisionAction crad = new ConfigRevisionAction();
            crad.setParentAction(newA);
            crad.setServer(newS);
            crad.setCreated(new Date());
            crad.setModified(new Date());

            // Setup the CRAResult
            ConfigRevisionActionResult cresult = new ConfigRevisionActionResult();
            cresult.setCreated(new Date());
            cresult.setModified(new Date());
            cresult.setConfigRevisionAction(crad);
            crad.setConfigRevisionActionResult(cresult);
            // Create ConfigRevision
            ConfigRevision cr = ConfigTestUtils.createConfigRevision(usr.getOrg());
            crad.setConfigRevision(cr);
            ConfigAction ca = (ConfigAction) newA;
            ca.addConfigRevisionAction(crad);
            ca.addServerAction(createServerAction(newS, newA));
        }
        else if (type.equals(ActionFactory.TYPE_SCRIPT_RUN)) {
            ScriptActionDetails sad = new ScriptActionDetails();
            sad.setUsername("AFTestTestUser");
            sad.setGroupname("AFTestTestGroup");
            String script = "#!/bin/csh\nls -al";
            sad.setScript(script.getBytes("UTF-8"));
            sad.setTimeout(new Long(9999));
            sad.setParentAction(newA);
            ((ScriptRunAction) newA).setScriptActionDetails(sad);
        }
        else if (type.equals(ActionFactory.TYPE_KICKSTART_INITIATE) ||
                type.equals(ActionFactory.TYPE_KICKSTART_SCHEDULE_SYNC)) {
            KickstartActionDetails ksad = new KickstartActionDetails();
            ksad.setStaticDevice("eth0");
            ksad.setParentAction(newA);
            ((KickstartAction) newA).setKickstartActionDetails(ksad);
        }
        else if (type.equals(ActionFactory.TYPE_PACKAGES_AUTOUPDATE) ||
                type.equals(ActionFactory.TYPE_PACKAGES_DELTA) ||
                type.equals(ActionFactory.TYPE_PACKAGES_REFRESH_LIST) ||
                type.equals(ActionFactory.TYPE_PACKAGES_REMOVE) ||
                type.equals(ActionFactory.TYPE_PACKAGES_RUNTRANSACTION) ||
                type.equals(ActionFactory.TYPE_PACKAGES_UPDATE) ||
                type.equals(ActionFactory.TYPE_PACKAGES_VERIFY)) {

            PackageActionDetails d = new PackageActionDetails();
            String parameter = "upgrade";
            d.setParameter(parameter);

            //create packageArch
            Long testid = new Long(100);
            String query = "PackageArch.findById";
            PackageArch arch = (PackageArch) TestUtils.lookupFromCacheById(testid, query);
            d.setArch(arch);

            //create packageName
            String testname = "Test Name " + TestUtils.randomString();
            PackageName name = new PackageName();
            name.setName(testname);
            d.setPackageName(name);
            TestUtils.saveAndFlush(name);

            //create packageEvr
            PackageEvr evr = PackageEvrFactory.lookupOrCreatePackageEvr("" +
                    System.currentTimeMillis(), "2.0", "1.0");
            d.setEvr(evr);
            ((PackageAction) newA).addDetail(d);
        }
        // Here we specifically want to test the addition of the ServerAction details
        // objects.
        else if (type.equals(ActionFactory.TYPE_REBOOT)) {
            usr.addPermanentRole(RoleFactory.ORG_ADMIN);
            Server newS = ServerFactoryTest.createTestServer(usr, true);
            newA.addServerAction(ServerActionTest.createServerAction(newS, newA));
        }
        else if (type.equals(ActionFactory.TYPE_DAEMON_CONFIG)) {
            DaemonConfigDetails dcd = new DaemonConfigDetails();
            dcd.setRestart("Y");
            dcd.setInterval(new Long(1440));
            dcd.setDaemonConfigCreated(new Date());
            dcd.setDaemonConfigModified(new Date());
            dcd.setParentAction(newA);
            ((DaemonConfigAction) newA).setDaemonConfigDetails(dcd);
        }

        newA.setName("RHN-JAVA Test Action");
        newA.setActionType(type);
        newA.setOrg(usr.getOrg());
        newA.setEarliestAction(new Date());
        newA.setVersion(new Long(0));
        newA.setArchived(new Long(0));
        newA.setCreated(new Date());
        newA.setModified(new Date());
        ActionFactory.save(newA);
        return newA;
    }