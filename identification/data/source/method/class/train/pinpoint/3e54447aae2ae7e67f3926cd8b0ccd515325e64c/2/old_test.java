    @Test
    public void toJvmSignature() {
        Assert.assertEquals(JavaAssistUtils.toJvmSignature("int"), "I");
        Assert.assertEquals(JavaAssistUtils.toJvmSignature("int[]"), "[I");
        Assert.assertEquals(JavaAssistUtils.toJvmSignature("int[][][]"), "[[[I");

        Assert.assertEquals(JavaAssistUtils.toJvmSignature("void"), "V");

        Assert.assertEquals(JavaAssistUtils.toJvmSignature("java.lang.String"), "Ljava/lang/String;");
        Assert.assertEquals(JavaAssistUtils.toJvmSignature("java.lang.String[][]"), "[[Ljava/lang/String;");

        try {
            Assert.assertEquals(JavaAssistUtils.toJvmSignature(""), "");
            Assert.fail("empty string");
        } catch (Exception ignore) {
        }

        try {
            Assert.assertEquals(JavaAssistUtils.toJvmSignature(null), null);
            Assert.fail("null");
        } catch (Exception ignore) {
        }
    }