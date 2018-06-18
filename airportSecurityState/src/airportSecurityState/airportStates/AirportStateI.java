package airportSecurityState.airportStates;

interface AirportStateI
{
   /**
    * Function used to control the state the program is in
    *
    * @param double - avg travellers
    * @param double - avg weapons
    * @void - nothing is returned
    */
    public void tightenOrLoosenSecurity(int day, String item);

   /**
    * Function used to pass operations of a State to an Airport
    *
    * @return int [] - subset of operations
    */
    public int[] getOperations();
}
