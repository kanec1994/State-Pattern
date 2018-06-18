package airportSecurityState.airportStates;

import airportSecurityState.util.MyLogger;

class SecurityFactors
{
    private int totalTravellers, totalProhibitedItems, numDaysPassed;
    private double avgTrafficDaily, avgProhibitedItemsDaily;

   /**
    * Constructor for the SecurityFactors class. Initializes totalTravellers,
    * totalProhibitedItems, numDaysPassed, avgTrafficDaily, and
    * avgProhibitedItemsDaily to 0.
    *
    * @return - no return value
    */
    public SecurityFactors()
    {
	MyLogger.writeMessage("SecurityFactors Constructor Called", MyLogger.DebugLevel.CONSTRUCTOR);
	totalTravellers = 0;
	totalProhibitedItems = 0;
        numDaysPassed = 0;
	avgTrafficDaily = 0.0;
	avgProhibitedItemsDaily = 0.0;
    }

   /**
    * This private function updates the avgs of travellers
    * and prohibited items.
    *
    * @return void - nothing is returned
    */
    public void updateStatistics(int day, String item)
    {
        totalTravellers += 1;
	numDaysPassed = day;
	checkItem(item);

	avgTrafficDaily = (totalTravellers * 1.0)/numDaysPassed;
	avgProhibitedItemsDaily = (totalProhibitedItems * 1.0)/numDaysPassed;
	MyLogger.writeMessage("Updated Statistics - \navgTrafficDaily: " + avgTrafficDaily + 
				"\navgProhibitedItemsDaily: " + avgProhibitedItemsDaily + "\n", MyLogger.DebugLevel.PERFORM_CALC);
    }

   /**
    * This is a private function which processes an item to check if it is
    * prohibited.
    *
    * @param String - item in question
    * @return void - nothing is returned
    */
    private void checkItem(String item)
    {
	if(item.equals("Gun"))
	{
	    totalProhibitedItems += 1;
	}
	else if(item.equals("NailCutter"))
	{
	    totalProhibitedItems += 1;
	}
	else if(item.equals("Blade"))
	{
	    totalProhibitedItems += 1;
	}
	else if(item.equals("Knife"))
	{
	    totalProhibitedItems += 1;
	}
    }

   /**
    * This acts as a getter for the avgTravellers
    *
    * @return int[] - subset of operations
    */
    public double getAvgTravellers()
    {
        return avgTrafficDaily;
    }

   /**
    * This acts as a getter for the avgWeapons
    *
    * @return int[] - subset of operations
    */
    public double getAvgWeapons()
    {
	return avgProhibitedItemsDaily;
    }
}
