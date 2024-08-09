public void scrutinize(Snak snak, EntityIdValue entityId, boolean added) {
    	if(snak instanceof ValueSnak) {
    		scrutinize((ValueSnak)snak, entityId, added);
    	} else if(snak instanceof NoValueSnak) {
    		scrutinize((NoValueSnak)snak, entityId, added);
    	} else if(snak instanceof SomeValueSnak) {
    		scrutinize((SomeValueSnak)snak, entityId, added);
    	}
    }