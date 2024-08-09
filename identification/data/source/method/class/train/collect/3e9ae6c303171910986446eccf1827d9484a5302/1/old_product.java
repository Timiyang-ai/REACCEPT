public static AuditEventType getAuditEventTypeFromFecType(int fcEvent) {
        AuditEventType auditEventType;
        switch (fcEvent) {
            case FormEntryController.EVENT_BEGINNING_OF_FORM:
                auditEventType = AuditEventType.BEGINNING_OF_FORM;
                break;
            case FormEntryController.EVENT_QUESTION:
                auditEventType = AuditEventType.QUESTION;
                break;
            case FormEntryController.EVENT_GROUP:
                auditEventType = AuditEventType.GROUP;
                break;
            case FormEntryController.EVENT_REPEAT:
                auditEventType = AuditEventType.REPEAT;
                break;
            case FormEntryController.EVENT_PROMPT_NEW_REPEAT:
                auditEventType = AuditEventType.PROMPT_NEW_REPEAT;
                break;
            case FormEntryController.EVENT_END_OF_FORM:
                auditEventType = AuditEventType.END_OF_FORM;
                break;
            default:
                auditEventType = AuditEventType.UNKNOWN_EVENT_TYPE;
        }
        return auditEventType;
    }