    private void getDates(java.util.Date startRange, java.util.Date endRange, java.util.Date eventStart, Recur recur) { 
        
        net.fortuna.ical4j.model.Date start = new net.fortuna.ical4j.model.Date(startRange); 
        net.fortuna.ical4j.model.Date end = new net.fortuna.ical4j.model.Date(endRange); 
        net.fortuna.ical4j.model.Date seed = new net.fortuna.ical4j.model.Date(eventStart); 
         
        DateList dates = recur.getDates(seed, start, end, Value.DATE); 
        for (int i=0; i<dates.size(); i++) { 
            log.info("date_" + i + " = " + dates.get(i).toString()); 
        } 
    }