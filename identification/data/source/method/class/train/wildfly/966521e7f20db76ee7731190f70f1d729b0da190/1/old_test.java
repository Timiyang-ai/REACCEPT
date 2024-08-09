@Test
    public void testRollback() throws Exception {

        final File binDir = createInstalledImage(env, "consoleSlot", productConfig.getProductName(), productConfig.getProductVersion());

        // build a one-off patch for the base installation
        // with 1 updated file
        String patchID = randomString();
        String patchElementId = randomString();
        File patchDir = mkdir(tempDir, patchID);

        // create a module for the conflict
        File baseModuleDir = newFile(env.getInstalledImage().getModulesDir(), SYSTEM, LAYERS, BASE);
        String moduleConflictName = "module-conflict";
        File moduleConflictDir = createModule0(baseModuleDir, moduleConflictName);
        // create the patch with the updated module
        ContentModification moduleConflictModified = ContentModificationUtils.modifyModule(patchDir, patchElementId, moduleConflictDir, "new resource in the module");

        // create a module to be updated w/o a conflict
        String moduleNoConflictName = "module-no-conflict";
        File moduleNoConflictDir = createModule0(baseModuleDir, moduleNoConflictName);
        // create the patch with the updated module
        ContentModification moduleNoConflictModified = ContentModificationUtils.modifyModule(patchDir, patchElementId, moduleNoConflictDir, "new resource in the module");

        // create a file for the conflict
        String fileConflictName = "file-conflict.txt";
        File conflictFile = touch(binDir, fileConflictName);
        dump(conflictFile, "original script to run standalone AS7");
        // patch the file
        ContentModification fileConflictModified = ContentModificationUtils.modifyMisc(patchDir, patchID, "updated script", conflictFile, "bin", fileConflictName);

        // create a file for the conflict
        String fileNoConflictName = "file-no-conflict.txt";
        File noConflictFile = touch(binDir, fileNoConflictName);
        dump(noConflictFile, "original script to run standalone AS7");
        // patch the file
        ContentModification fileNoConflictModified = ContentModificationUtils.modifyMisc(patchDir, patchID, "updated script", noConflictFile, "bin", fileNoConflictName);

        // create a bundle for the conflict
        File baseBundleDir = newFile(env.getInstalledImage().getBundlesDir(), SYSTEM, LAYERS, BASE);
        String bundleConflictName = "bundle-conflict";
        File bundleConflictDir = createBundle0(baseBundleDir, bundleConflictName, "bundle content");
        // patch the bundle
        ContentModification bundleConflictModified = ContentModificationUtils.modifyBundle(patchDir, patchElementId, bundleConflictDir, "updated bundle content");

        // create a bundle to be updated w/o a conflict
        String bundleNoConflictName = "bundle-no-conflict";
        File bundleNoConflictDir = createBundle0(baseBundleDir, bundleNoConflictName, "bundle content");
        // patch the bundle
        ContentModification bundleNoConflictModified = ContentModificationUtils.modifyBundle(patchDir, patchElementId, bundleNoConflictDir, "updated bundle content");

        //TestUtils.tree(env.getInstalledImage().getJbossHome());

        Patch patch = PatchBuilder.create()
                .setPatchId(patchID)
                .setDescription(randomString())
                .oneOffPatchIdentity(productConfig.getProductName(), productConfig.getProductVersion())
                .getParent()
                .addContentModification(fileConflictModified)
                .addContentModification(fileNoConflictModified)
                .oneOffPatchElement(patchElementId, "base", false)
                .addContentModification(moduleConflictModified)
                .addContentModification(moduleNoConflictModified)
                .addContentModification(bundleConflictModified)
                .addContentModification(bundleNoConflictModified)
                .getParent()
                .build();

        // create the patch
        createPatchXMLFile(patchDir, patch, false);

        File zippedPatch = createZippedPatchFile(patchDir, patch.getPatchId());

        // apply the patch using the cli
        CommandContext ctx = CommandContextFactory.getInstance().newCommandContext();
        try {
            ctx.handle("patch apply " + zippedPatch.getAbsolutePath() + " --distribution=" + env.getInstalledImage().getJbossHome());
        } catch(CommandLineException e) {
            ctx.terminateSession();
            fail("Failed to apply the patch: " + e);
        }

        // create a conflict for the file
        dump(conflictFile, "conflicting change");
        // create a conflict for the module
        createModule0(baseModuleDir, moduleConflictName, "oops");
        // create a conflict for bundle
        createBundle0(baseBundleDir, bundleConflictName, "oops");

        try {
            ctx.handle("patch rollback --patch-id=" + patchID + " --distribution=" + env.getInstalledImage().getJbossHome() + " --reset-configuration=false");
            fail("Conflicts expected");
        } catch(CommandLineException e) {
            final int relativeIndex = env.getInstalledImage().getJbossHome().getAbsolutePath().length() + 1;
            // TODO modules and bundles are not checked at the moment
            //assertConflicts(e, bundleConflictName + ":main", moduleConflictName + ":main", conflictFile.getAbsolutePath().substring(relativeIndex));
            assertConflicts(e, conflictFile.getAbsolutePath().substring(relativeIndex));
        } finally {
            ctx.terminateSession();
        }

    }