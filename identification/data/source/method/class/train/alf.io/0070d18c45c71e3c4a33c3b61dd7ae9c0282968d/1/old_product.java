public static Map<String, Object> buildModelForTicketEmail(Organization organization,
                                                               Event event,
                                                               TicketReservation ticketReservation,
                                                               String ticketURL,
                                                               Ticket ticket,
                                                               TicketCategory ticketCategory) {
        Map<String, Object> model = new HashMap<>();
        model.put("organization", organization);
        model.put("event", event);
        model.put("ticketReservation", ticketReservation);
        model.put("ticketUrl", ticketURL);
        model.put("ticket", ticket);
        model.put("googleCalendarUrl", EventUtil.getGoogleCalendarURL(event, ticketCategory, null));
        fillTicketValidity(event, ticketCategory, model);
        return model;
    }