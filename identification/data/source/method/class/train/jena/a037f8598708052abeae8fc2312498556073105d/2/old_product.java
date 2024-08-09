public String toString()
    {
        StringWriter stringWriter = new StringWriter(512) ;
        PrintWriter pw = new PrintWriter(stringWriter) ;

        pw.print("SELECT  ") ;
        if ( resultVars.size() == 0 )
        {
            pw.print("*") ;
            pw.println() ;
        }
        else
        {
            boolean first = true ;
            for ( Iterator iter = resultVars.iterator() ; iter.hasNext() ; )
            {
                String var = (String)iter.next() ;
                if ( ! first )
                    pw.print(", ") ;
                pw.print("?") ;
                pw.print(var) ;
                first = false ;
            }
            pw.println() ;
        }

        // Source

        // Triple patterns
        if ( triplePatterns.size() > 0 )
        {
            pw.print("WHERE   ") ;
            boolean first = true ;
            for ( Iterator iter = triplePatterns.iterator() ; iter.hasNext() ; )
            {
                TriplePattern tp = (TriplePattern)iter.next() ;
                if ( ! first )
                {
                    pw.print(", ") ;
                    pw.println() ;
                    pw.print("        ") ;
                }
                pw.print(tp.toString()) ;
                first = false ;
            }
            pw.println() ;
        }

        // Constraints
        if ( constraints.size() > 0 )
        {
            /* Old code - print all on one line, separated by commas
            boolean first = true ;
            for ( Iterator iter = constraints.iterator() ; iter.hasNext() ; )
            {
                Constraint c = (Constraint)iter.next() ;
                if ( ! first )
                    pw.print(", ") ;
                pw.print(c.toString()) ;
                first = false ;
            }
            pw.println() ;
            */
            for ( Iterator iter = constraints.iterator() ; iter.hasNext() ; )
            {
                Constraint c = (Constraint)iter.next() ;
                pw.print("AND     ") ;
                pw.println(c.toString()) ;
            }
        }

		if ( prefixMap.size() > 0 )
		{
			pw.println("USING") ;
            boolean first = true ;
			for ( Iterator iter = prefixMap.keySet().iterator() ; iter.hasNext() ; )
			{
                if ( ! first )
                    pw.println(" ,") ;
				String k = (String)iter.next() ;
				String v = (String)prefixMap.get(k) ;
				pw.print("    "+k+" FOR <"+v+">") ;
                first = false ;
			}
            pw.println() ;
		}
        
        pw.flush() ;
        pw.close() ;
        return stringWriter.getBuffer().toString() ;
    }