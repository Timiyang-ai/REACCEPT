    @Test
    public void initLocalVariables() throws Exception {
        final String className = "com.navercorp.pinpoint.profiler.instrument.mock.ArgsClass";
        final MethodNode methodNode = ASMClassNodeLoader.get(className, "argByteType");
        String[] exceptions = null;
        if (methodNode.exceptions != null) {
            exceptions = methodNode.exceptions.toArray(new String[0]);
        }

        final ASMMethodNodeAdapter methodNodeAdapter = new ASMMethodNodeAdapter("foo", new MethodNode(methodNode.access, methodNode.name, methodNode.desc, methodNode.signature, exceptions));
        ASMMethodVariables variables = new ASMMethodVariables(JavaAssistUtils.javaNameToJvmName(className), methodNodeAdapter.getMethodNode());
        assertEquals(0, variables.getLocalVariables().size());

        InsnList instructions = new InsnList();
        variables.initLocalVariables(instructions);

        assertEquals(2, variables.getLocalVariables().size());
        assertEquals("this", variables.getLocalVariables().get(0).name);
        assertEquals("Lcom/navercorp/pinpoint/profiler/instrument/mock/ArgsClass;", variables.getLocalVariables().get(0).desc);
        assertEquals("byte", variables.getLocalVariables().get(1).name);
        assertEquals("B", variables.getLocalVariables().get(1).desc);
    }