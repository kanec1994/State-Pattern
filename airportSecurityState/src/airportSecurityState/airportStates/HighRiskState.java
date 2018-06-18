package airportSecurityState.airportStates;

import airportSecurityState.util.MyLogger;

class HighRiskState implements AirportStateI
{
    private Airport currentAirport;
    private int[] operationIDs;
    private SecurityFactors securityFactors;
	
   /**  
    * Constructor for the high risk state of an airport.     
    * Initializes the currentAirport to the context of the      
    * Airport object passed into the class                          
    *                                                 
    * @param Airport - the context for which Airport this             
    *                  class refers to.      
    * @return - no return value for constructor       
    */
    public HighRiskState(Airport airport, SecurityFactors factors)
    {
	MyLogger.writeMessage("HighRiskState Constructor Called", MyLogger.DebugLevel.CONSTRUCTOR);
	currentAirport = airport;
	securityFactors = factors;
	operationIDs = new int[] {2, 4, 6, 8, 10};
    }

   /**                                                                                                                                                                                   
    * Implements the tightenOrLoosenSecurity method of the           
    * AirportStateI interface which this class uses. 
    * 
    * NOTE: Even though the criteria in the assignment says   
    * 4 <= avgTraffic < 8 OR 1 <= avgWeapons < 2
    * for MODERATE_RISK and
    * 0 <= avgTraffic < 4 OR 0 <= avgWeapons < 1   
    * for LOW_RISK,
    * in order to transfer to moderate or low risk here we    
    * need to ensure both criteria are in low, since if only        
    * one criteria is, it will default to a higher state.  
    *    log = logger;
	log.writeMessage("Airport Constructor Called", 4);
    * @param int - days passed
    * @param String - item brought by traveller   
    * @void - nothing to be returned                  
    */
    public void tightenOrLoosenSecurity(int day, String item)
    {
	double avgTraffic, avgWeapons;
	securityFactors.updateStatistics(day, item);
	avgTraffic = securityFactors.getAvgTravellers();
	avgWeapons = securityFactors.getAvgWeapons();

        if((avgTraffic < 4.0) && (avgWeapons < 1.0))
	{
	    currentAirport.setAirportState("low");
	}
	else if((avgTraffic < 8.0) && (avgWeapons < 2.0))
	{
	    currentAirport.setAirportState("moderate");
	}
    }

   /**
    * This acts as a getter for the subset of operations needed
    * to be performed by this state
    *
    * @return int[] - subset of operations
    */
    public int[] getOperations()
    {
	return operationIDs;
    }
}
