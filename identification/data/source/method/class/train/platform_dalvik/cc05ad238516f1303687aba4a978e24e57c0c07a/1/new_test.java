@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "add",
        args = {java.lang.Object.class}
    )
    @SuppressWarnings("unchecked")
    public void test_add_E() {
        Set<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        set.add(EnumFoo.a);
        set.add(EnumFoo.b);
        
        try {
            set.add(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }
        
        // test enum type with more than 64 elements
        Set rawSet = set;
        try {
            rawSet.add(HugeEnumWithInnerClass.b);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // expected
        }

        set.clear();
        try {
            set.add(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        boolean result = set.add(EnumFoo.a);
        assertEquals("Size should be 1:", 1, set.size()); //$NON-NLS-1$
        assertTrue("Return value should be true", result); //$NON-NLS-1$

        result = set.add(EnumFoo.a);
        assertEquals("Size should be 1:", 1, set.size()); //$NON-NLS-1$
        assertFalse("Return value should be false", result); //$NON-NLS-1$

        set.add(EnumFoo.b);
        assertEquals("Size should be 2:", 2, set.size()); //$NON-NLS-1$
        
        rawSet = set;
        try {
            rawSet.add(EnumWithAllInnerClass.a);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch(ClassCastException e) {
            // expected
        }
        
        try {
            rawSet.add(EnumWithInnerClass.a);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch(ClassCastException e) {
            // expected
        }
        
        try {
            rawSet.add(new Object());
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch(ClassCastException e) {
            // expected
        }
        
        // test enum type with more than 64 elements
        Set<HugeEnum> hugeSet = EnumSet.noneOf(HugeEnum.class);
        result = hugeSet.add(HugeEnum.a);
        assertTrue(result);

        result = hugeSet.add(HugeEnum.a);
        assertFalse(result);

        try {
            hugeSet.add(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        rawSet = hugeSet;
        try {
            rawSet.add(HugeEnumWithInnerClass.b);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // expected
        }

        try {
            rawSet.add(new Object());
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // expected
        }

        result = hugeSet.add(HugeEnum.mm);
        assertTrue(result);
        result = hugeSet.add(HugeEnum.mm);
        assertFalse(result);
        assertEquals(2, hugeSet.size());
        
    }