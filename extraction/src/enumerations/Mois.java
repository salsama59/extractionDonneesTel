package enumerations;

public enum Mois 
{
	JANVIER (0),
	
	FEVRIER(1),
	
	MARS(2),
	
	AVRIL(3),
	
	MAI(4),
	
	JUIN(5),
	
	JUILLET(6),
	
	AOUT(7),
	
	SEPTEMBRE(8),
	
	OCTOBRE(9),
	
	NOVEMBRE(10),
	
	DECEMBRE(11);
	
	private final long id;
	
	private Mois(long numeroMois)
	{
		id = numeroMois;
	}
    
    public static int getNumeroMois(String mois)
    {
    	
        for (Mois type : values())
        {
            if (type.name().equals(mois.toUpperCase()))
            {
                return type.ordinal();
            }
        }
        
        return -1;
    }
    
    public static String getNomMois(final long numero)
    {
        for (Mois type : values())
        {
            if (type.id == numero)
            {
                return type.name().toLowerCase();
            }
        }
        
		return null;
		
    }

	public long getId() {
		return id;
	}
}
