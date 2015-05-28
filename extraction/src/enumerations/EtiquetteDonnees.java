/**
 * 
 */
package enumerations;

/**
 * @author salsama
 *
 */

public enum EtiquetteDonnees 
{
	NOM (0),
	
	CODE(1),
	
	NBAPPELS(2),
	
	COUT(3),
	
	DUREE(4);
	
	private final long id;
	
	private EtiquetteDonnees(final long numeroEtiquette)
	{
		id = numeroEtiquette;
	}
	
    public long getId()
    {
        return id;
    }

    public static EtiquetteDonnees get(final long numeroEtiquette)
    {
        for (EtiquetteDonnees type : values())
        {
            if (type.id == numeroEtiquette)
            {
                return type;
            }
        }
        
        return null;
    }
    
    public static String getNom(final long numeroEtiquette)
    {
        for (EtiquetteDonnees type : values())
        {
            if (type.id == numeroEtiquette)
            {
                return type.toString();
            }
        }
        
        return "";
    }
}
