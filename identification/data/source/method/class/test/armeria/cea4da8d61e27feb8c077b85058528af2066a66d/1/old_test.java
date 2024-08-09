    @Test
    public void getAnnotations_declared() {
        final List<Annotation> list = getAnnotations(TestGrandChildClass.class);
        assertThat(list).hasSize(2);
        assertThat(list.get(0)).isInstanceOf(TestRepeatables.class);
        assertThat(list.get(1)).isInstanceOf(TestAnnotation.class);
    }