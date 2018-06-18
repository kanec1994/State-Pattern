package airportSecurityState.airportStates;

import airportSecurityState.util.MyLogger;

class LowRiskState implements AirportStateI
{
    private Airport currentAirport;
    private int[] operationIDs;
    private SecurityFactors securityFactors;
	
   /**  
    * Constructor for the low risk state of an airport.     
    * Initializes the currentAirport to the context of the      
    * Airport object passed into the class.
    *                
    * @param Airport - the context for which Airport this      
    *                  class refers to.       
    * @return - no return value for constructor 
    */
    public LowRiskState(Airport airport, SecurityFactors factors)
    {
	MyLogger.writeMessage("LowRiskState Constructor Called", MyLogger.DebugLevel.CONSTRUCTOR);
	currentAirport = airport;
	securityFactors = factors;
	operationIDs = new int[] {1, 3, 5, 7, 9};
    }

   /**
    * Implements the tightenOrLoosenSecurity method of the
    * AirportStateI interface which this class uses.
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
	else if((avgTraffic >= 4.0) || (avgWeapons >= 1.0))
	{
	    currentAirport.setAirportState("moderate");
	}
    }

   /**
    * This function acts as a getter to return the operations
    * needed to perform after processing a traveller entering
    * the airport.
    *
    * @return int[] - the subset of operationIDs to be performed
    */
    public int[] getOperations()
    {
	return operationIDs;
    }
}
