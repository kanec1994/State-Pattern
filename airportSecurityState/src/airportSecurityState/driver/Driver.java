import airportSecurityState.util.Results;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.FileProcessor;
import airportSecurityState.airportStates.Airport;

import java.util.ArrayList;

public class Driver
{
    public static void main(String[] args)
    {
	if(args.length == 3)
	{
	    MyLogger.setDebugValue(Integer.parseInt(args[2]));
	    FileProcessor airportData = new FileProcessor(args[0]);
	    Results securityResults = new Results(args[1]);

	    Airport airport = new Airport();

	    String traveller = airportData.readLine();
	    while(traveller != null)
	    {
		String[] dataList = traveller.split(";|\\:");
		int day = Integer.parseInt(dataList[1]);
		String item = dataList[8];

		securityResults = airport.processTraveller(day, item, securityResults);
		traveller = airportData.readLine();
	    }

	    securityResults.printResults();
	    
	    airportData.close();
	    securityResults.close();
	}
	else
	{
	    System.err.println("Incorrect Number of arguments given!");
	}
    }
}
