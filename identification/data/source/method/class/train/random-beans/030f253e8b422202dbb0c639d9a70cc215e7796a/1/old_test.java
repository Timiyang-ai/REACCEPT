    @Test
    void getWrapperTypeTest() {
        assertThat(ReflectionUtils.getWrapperType(Byte.TYPE)).isEqualTo(Byte.class);
        assertThat(ReflectionUtils.getWrapperType(Short.TYPE)).isEqualTo(Short.class);
        assertThat(ReflectionUtils.getWrapperType(Integer.TYPE)).isEqualTo(Integer.class);
        assertThat(ReflectionUtils.getWrapperType(Long.TYPE)).isEqualTo(Long.class);
        assertThat(ReflectionUtils.getWrapperType(Double.TYPE)).isEqualTo(Double.class);
        assertThat(ReflectionUtils.getWrapperType(Float.TYPE)).isEqualTo(Float.class);
        assertThat(ReflectionUtils.getWrapperType(Boolean.TYPE)).isEqualTo(Boolean.class);
        assertThat(ReflectionUtils.getWrapperType(Character.TYPE)).isEqualTo(Character.class);
        assertThat(ReflectionUtils.getWrapperType(String.class)).isEqualTo(String.class);
    }