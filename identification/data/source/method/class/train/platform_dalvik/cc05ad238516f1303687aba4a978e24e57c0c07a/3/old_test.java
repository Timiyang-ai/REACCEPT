@TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Functional test.",
      targets = {
        @TestTarget(
          methodName = "JarOutputStream",
          methodArgs = {java.io.OutputStream.class, java.util.jar.Manifest.class}
        )
    })
    public void test_main_class_in_another_jar() throws Exception {
        File fooJar = File.createTempFile("hyts_", ".jar");
        File barJar = File.createTempFile("hyts_", ".jar");
        fooJar.deleteOnExit();
        barJar.deleteOnExit();
        
        // create the manifest
        Manifest man = new Manifest();
        Attributes att = man.getMainAttributes();
        att.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        att.put(Attributes.Name.MAIN_CLASS, "foo.bar.execjartest.Foo");
        att.put(Attributes.Name.CLASS_PATH, fooJar.getName());
        
        File resources = Support_Resources.createTempFolder();

        JarOutputStream joutFoo = new JarOutputStream(new FileOutputStream(fooJar));
        joutFoo.putNextEntry(new JarEntry("foo/bar/execjartest/Foo.class"));
        joutFoo.write(getResource(resources, "hyts_Foo.ser"));
        joutFoo.close();
        
        JarOutputStream joutBar = new JarOutputStream(new FileOutputStream(barJar), man);
        joutBar.putNextEntry(new JarEntry("foo/bar/execjartest/Bar.class"));
        joutBar.write(getResource(resources, "hyts_Bar.ser"));
        joutBar.close();

        String[] args = new String[] {"-jar", barJar.getAbsolutePath()};

        // execute the JAR and read the result
        String    res = Support_Exec.execJava(args, null, false);

        assertTrue("Error executing JAR : result returned was incorrect.", res
                .startsWith("FOOBAR"));
    }