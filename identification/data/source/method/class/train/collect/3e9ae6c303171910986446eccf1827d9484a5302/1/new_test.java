    @Test
    public void getAuditEventTypeFromFecTypeTest() {
        assertEquals(BEGINNING_OF_FORM, AuditEvent.getAuditEventTypeFromFecType(FormEntryController.EVENT_BEGINNING_OF_FORM));
        assertEquals(GROUP, AuditEvent.getAuditEventTypeFromFecType(FormEntryController.EVENT_GROUP));
        assertEquals(REPEAT, AuditEvent.getAuditEventTypeFromFecType(FormEntryController.EVENT_REPEAT));
        assertEquals(PROMPT_NEW_REPEAT, AuditEvent.getAuditEventTypeFromFecType(FormEntryController.EVENT_PROMPT_NEW_REPEAT));
        assertEquals(END_OF_FORM, AuditEvent.getAuditEventTypeFromFecType(FormEntryController.EVENT_END_OF_FORM));
        assertEquals(UNKNOWN_EVENT_TYPE, AuditEvent.getAuditEventTypeFromFecType(100));
    }