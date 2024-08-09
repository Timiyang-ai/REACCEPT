    @Test
    public void field() {
        // instance field
        Test1 test1 = new Test1();
        ReflectUtils.reflect(test1).field("I_INT1", 1);
        assertEquals(1, ReflectUtils.reflect(test1).field("I_INT1").get());

        ReflectUtils.reflect(test1).field("I_INT2", 1);
        assertEquals(1, ReflectUtils.reflect(test1).field("I_INT2").get());

        ReflectUtils.reflect(test1).field("I_INT2", null);
        assertNull(ReflectUtils.reflect(test1).field("I_INT2").get());

        // static field
        ReflectUtils.reflect(Test1.class).field("S_INT1", 1);
        assertEquals(1, ReflectUtils.reflect(Test1.class).field("S_INT1").get());

        ReflectUtils.reflect(Test1.class).field("S_INT2", 1);
        assertEquals(1, ReflectUtils.reflect(Test1.class).field("S_INT2").get());

        ReflectUtils.reflect(Test1.class).field("S_INT2", null);
        assertNull(ReflectUtils.reflect(Test1.class).field("S_INT2").get());

        // hierarchies field
        TestHierarchicalMethodsSubclass test2 = new TestHierarchicalMethodsSubclass();

        ReflectUtils.reflect(test2).field("invisibleField1", 1);
        assertEquals(1, ReflectUtils.reflect(test2).field("invisibleField1").get());

        ReflectUtils.reflect(test2).field("invisibleField2", 1);
        assertEquals(1, ReflectUtils.reflect(test2).field("invisibleField2").get());

        ReflectUtils.reflect(test2).field("invisibleField3", 1);
        assertEquals(1, ReflectUtils.reflect(test2).field("invisibleField3").get());

        ReflectUtils.reflect(test2).field("visibleField1", 1);
        assertEquals(1, ReflectUtils.reflect(test2).field("visibleField1").get());

        ReflectUtils.reflect(test2).field("visibleField2", 1);
        assertEquals(1, ReflectUtils.reflect(test2).field("visibleField2").get());

        ReflectUtils.reflect(test2).field("visibleField3", 1);
        assertEquals(1, ReflectUtils.reflect(test2).field("visibleField3").get());
    }