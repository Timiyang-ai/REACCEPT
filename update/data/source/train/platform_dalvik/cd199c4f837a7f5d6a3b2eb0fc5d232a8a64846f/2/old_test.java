@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "getManifest",
        args = {}
    )
    public void test_getManifest() {
        // Test for method java.util.jar.Manifest
        // java.util.jar.JarFile.getManifest()
        try {
            Support_Resources.copyFile(resources, null, jarName);
            JarFile jarFile = new JarFile(new File(resources, jarName));
            assertNotNull("Error--Manifest not returned", jarFile.getManifest());
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during 1st test: " + e.toString());
        }
        try {
            Support_Resources.copyFile(resources, null, jarName2);
            JarFile jarFile = new JarFile(new File(resources, jarName2));
            assertNull("Error--should have returned null", jarFile
                    .getManifest());
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during 2nd test: " + e.toString());
        }

        try {
            // jarName3 was created using the following test
            Support_Resources.copyFile(resources, null, jarName3);
            JarFile jarFile = new JarFile(new File(resources, jarName3));
            assertNotNull("Should find manifest without verifying", jarFile
                    .getManifest());
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during 3rd test: " + e.toString());
        }

        try {
            // this is used to create jarName3 used in the previous test
            Manifest manifest = new Manifest();
            Attributes attributes = manifest.getMainAttributes();
            attributes.put(new Attributes.Name("Manifest-Version"), "1.0");
            ByteArrayOutputStream manOut = new ByteArrayOutputStream();
            manifest.write(manOut);
            byte[] manBytes = manOut.toByteArray();
            File file = File.createTempFile(
                    Support_PlatformFile.getNewPlatformFile("hyts_manifest1",
                            ""), ".jar");
            JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(
                    file.getAbsolutePath()));
            ZipEntry entry = new ZipEntry("META-INF/");
            entry.setSize(0);
            jarOut.putNextEntry(entry);
            entry = new ZipEntry(JarFile.MANIFEST_NAME);
            entry.setSize(manBytes.length);
            jarOut.putNextEntry(entry);
            jarOut.write(manBytes);
            entry = new ZipEntry("myfile");
            entry.setSize(1);
            jarOut.putNextEntry(entry);
            jarOut.write(65);
            jarOut.close();
            JarFile jar = new JarFile(file.getAbsolutePath(), false);
            assertNotNull("Should find manifest without verifying", jar
                    .getManifest());
            jar.close();
            file.delete();
        } catch (IOException e) {
            fail("IOException 3");
        }
        try {
            Support_Resources.copyFile(resources, null, jarName2);
            JarFile jF = new JarFile(new File(resources, jarName2));
            jF.close();
            jF.getManifest();
            fail("FAILED: expected IllegalStateException");
        } catch (IllegalStateException ise) {
            // expected;
        } catch (Exception e) {
            fail("Exception during 4th test: " + e.toString());
        }

        Support_Resources.copyFile(resources, null, "Broken_manifest.jar");
        JarFile jf;
        try {
            jf = new JarFile(new File(resources, "Broken_manifest.jar"));
            jf.getManifest();
            fail("IOException expected.");
        } catch (IOException e) {
            // expected.
        }
    }