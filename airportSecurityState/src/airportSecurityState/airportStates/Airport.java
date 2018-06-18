package airportSecurityState.airportStates;

import airportSecurityState.util.Results;
import airportSecurityState.util.MyLogger;

public class Airport
{
    private AirportStateI lowRiskState, moderateRiskState, highRiskState;
    private AirportStateI currentState;
    private SecurityFactors securityFactors;
    

   /**
    * Constructor for the airport class. Additionally creates instances of
    * the low, moderate, and high risk states for the airport. Initializes
    * the current State of the Airport to low risk.
    *
    * @return - no return value for a constructor
    */
    public Airport()
    {
	MyLogger.writeMessage("Airport Constructor Called", MyLogger.DebugLevel.CONSTRUCTOR);
        securityFactors = new SecurityFactors();
	lowRiskState = new LowRiskState(this, securityFactors);
	moderateRiskState = new ModerateRiskState(this, securityFactors);
	highRiskState = new HighRiskState(this, securityFactors);
	currentState = lowRiskState;
    }

   /**
    * Function is called for each new traveller that enters the Airport.
    * The function will call all necessary functions to process the traveller
    * and will store the result as needed. Then return the Results object to
    * the calling function.
    *
    * @param int - how many days have passed
    * @param String - item brought to airport by traveller
    * @param int[] - operations to perform
    */
    public Results processTraveller(int day, String item, Results securityResults)
    {
        currentState.tightenOrLoosenSecurity(day, item);
	securityResults.storeNewResult(currentState.getOperations());

	return securityResults;
    }

   /**
    * This function allows the state classes to update the state 
    * of the Airport
    *
    * @param String - level to set security to.
    * @return void - nothing is returned.
    */
    void setAirportState(String level)
    {
	if(level.equals("high"))
	{
	    MyLogger.writeMessage("Change to High Risk State", MyLogger.DebugLevel.STATE_CHANGE);
	    currentState = highRiskState;
	}
	else if(level.equals("moderate"))
	{
	    MyLogger.writeMessage("Change to Moderate Risk State", MyLogger.DebugLevel.STATE_CHANGE);
	    currentState = moderateRiskState;
	}
	else
	{
	    MyLogger.writeMessage("Change to Low Risk State", MyLogger.DebugLevel.STATE_CHANGE);
	    currentState = lowRiskState;
	}
    }
}
