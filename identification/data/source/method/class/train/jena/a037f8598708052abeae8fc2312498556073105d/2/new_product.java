public String toString()
    {
        StringBuffer sb = new StringBuffer() ; 

        sb.append("SELECT  ") ;
        if ( resultVars.size() == 0 )
            sb.append("*") ;
        else
        {
            boolean first = true ;
            for ( Iterator iter = resultVars.iterator() ; iter.hasNext() ; )
            {
                String var = (String)iter.next() ;
                if ( ! first )
                    sb.append(", ") ;
                sb.append("?") ;
                sb.append(var) ;
                first = false ;
            }
        }
        sb.append("\n") ;

        // Source

        // Triple patterns
        if ( triplePatterns.size() > 0 )
        {
            sb.append("WHERE   ") ;
            boolean first = true ;
            for ( Iterator iter = triplePatterns.iterator() ; iter.hasNext() ; )
            {
                Triple tp = (Triple)iter.next() ;
                if ( ! first )
                {
                    sb.append(", \n") ;
                    sb.append("        ") ;
                   
                }
                sb.append(triplePatternToString(tp)) ;
                first = false ;
            }
            sb.append("\n") ;
        }

        // Constraints
        if ( constraints.size() > 0 )
        {
            for ( Iterator iter = constraints.iterator() ; iter.hasNext() ; )
            {
                Constraint c = (Constraint)iter.next() ;
                sb.append("AND     ") ;
                sb.append(c.toString()) ;
                sb.append("\n") ;
            }
        }

		if ( prefixMap.size() > 0 )
		{
			sb.append("USING\n") ;
            boolean first = true ;
			for ( Iterator iter = prefixMap.keySet().iterator() ; iter.hasNext() ; )
			{
                if ( ! first )
                    sb.append(" ,\n") ;
				String k = (String)iter.next() ;
				String v = (String)prefixMap.get(k) ;
				sb.append("    "+k+" FOR <"+v+">") ;
                first = false ;
			}
            sb.append("\n") ;
		}
        
        return sb.toString() ;
    }