    @Test
    public void getValueFromText() {
        String text = "<org.apache.jmeter.protocol.jms.sampler.render.Person><name>Doe</name></org.apache.jmeter.protocol.jms.sampler.render.Person>";
        Serializable object = render.getValueFromText(text);
        assertObject(object, "Doe");
    }