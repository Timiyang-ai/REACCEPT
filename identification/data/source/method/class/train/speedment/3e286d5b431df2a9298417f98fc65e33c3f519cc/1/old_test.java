    @Test
    void isNullable() {
        assertFalse(BYTE.isNullable());
        assertFalse(SHORT.isNullable());
        assertFalse(INT.isNullable());
        assertFalse(LONG.isNullable());
        assertFalse(FLOAT.isNullable());
        assertFalse(DOUBLE.isNullable());
        assertFalse(CHAR.isNullable());
        assertFalse(BOOLEAN.isNullable());
        assertFalse(ENUM.isNullable());
        assertFalse(STRING.isNullable());
        assertFalse(BIG_DECIMAL.isNullable());

        assertTrue(BYTE_NULLABLE.isNullable());
        assertTrue(SHORT_NULLABLE.isNullable());
        assertTrue(INT_NULLABLE.isNullable());
        assertTrue(LONG_NULLABLE.isNullable());
        assertTrue(FLOAT_NULLABLE.isNullable());
        assertTrue(DOUBLE_NULLABLE.isNullable());
        assertTrue(CHAR_NULLABLE.isNullable());
        assertTrue(BOOLEAN_NULLABLE.isNullable());
        assertTrue(ENUM_NULLABLE.isNullable());
        assertTrue(STRING_NULLABLE.isNullable());
        assertTrue(BIG_DECIMAL_NULLABLE.isNullable());
    }