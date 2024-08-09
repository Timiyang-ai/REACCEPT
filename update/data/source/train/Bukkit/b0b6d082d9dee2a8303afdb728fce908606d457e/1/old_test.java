@SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void getData() {
        for (Material material : Material.values()) {
            Class clazz = material.getData();

            assertThat(material.getNewData((byte) 0), isA(clazz));
        }
    }