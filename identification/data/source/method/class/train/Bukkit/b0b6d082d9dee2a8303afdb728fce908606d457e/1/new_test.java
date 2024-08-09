@Test
    public void getData() {
        for (Material material : Material.values()) {
            Class<? extends MaterialData> clazz = material.getData();

            assertThat(material.getNewData((byte) 0), is(instanceOf(clazz)));
        }
    }