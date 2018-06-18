package airportSecurityState.airportStates;

import airportSecurityState.util.MyLogger;

class ModerateRiskState implements AirportStateI
{
    private Airport currentAirport;
    private int[] operationIDs;
    private SecurityFactors securityFactors;
	
   /**
    * Constructor for the moderate risk state of an airport.
    * Initializes the currentAirport to the context of the
    * Airport object passed into the class. Sets the subset
    * of operations to be performed in this state.
    *
    * @param Airport - the context for which Airport this
    *                  class refers to.
    * @return - no return value for constructor
    */
    public ModerateRiskState(Airport airport, SecurityFactors factors)
    {
	MyLogger.writeMessage("ModerateRiskState Constructor Called", MyLogger.DebugLevel.CONSTRUCTOR);
	currentAirport = airport;
	securityFactors = factors;
	operationIDs = new int[] {2, 3, 5, 8, 9};
    }

   /**                                                      
    * Implements the tightenOrLoosenSecurity method of the     
    * AirportStateI interface which this class uses.
    *
    * NOTE: Even though the criteria in the assignment says
    * 0 <= avgTraffic < 4 OR 0<= avgWeapons < 1
    * for LOW_RISK, in order to transfer to low risk here we
    * need to ensure both criteria are in low, since if only
    * one criteria is, it will default to a higher state.                                                                                  
    * 
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

	if((avgTraffic >= 8.0) || (avgWeapons >= 2.0))
	{
            currentAirport.setAirportState("high");
	}
	else if((avgTraffic < 4.0) && (avgWeapons < 1.0))
	{
	    currentAirport.setAirportState("low");
	}
    }

   /**
    * This function acts as a getter to return the subset
    * of operations needed to be performed.
    *
    * @return int[] - subset of operations
    */
    public int[] getOperations()
    {
        return operationIDs;
    }
}
