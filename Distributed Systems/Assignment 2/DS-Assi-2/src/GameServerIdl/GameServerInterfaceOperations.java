package GameServerIdl;

/**
 * Interface definition: GameServerInterface.
 * 
 * @author OpenORB Compiler
 */
public interface GameServerInterfaceOperations
{
    /**
     * Operation checkMethod
     */
    public void checkMethod(String param1, org.omg.CORBA.StringHolder reply);

    /**
     * Operation createPlayerAccount
     */
    public void createPlayerAccount(String firstname, String lastname, int age, String username, String password, String ipaddress, org.omg.CORBA.StringHolder success_status);

    /**
     * Operation playerSignIn
     */
    public void playerSignIn(String username, String password, String ipaddress, org.omg.CORBA.StringHolder success_status);

    /**
     * Operation playerSignOut
     */
    public void playerSignOut(String username, String ipaddress, org.omg.CORBA.StringHolder success_status);

    /**
     * Operation getPlayerStatus
     */
    public void getPlayerStatus(String username, String ipaddress, org.omg.CORBA.StringHolder success_status);

    /**
     * Operation suspendAccount
     */
    public void suspendAccount(String adminusername, String adminpassword, String adminipaddress, String usernametosuspend, org.omg.CORBA.StringHolder success_status);

    /**
     * Operation transferAccount
     */
    public void transferAccount(String username, String password, String oldipaddress, String newipaddress, org.omg.CORBA.StringHolder success_status);

}
