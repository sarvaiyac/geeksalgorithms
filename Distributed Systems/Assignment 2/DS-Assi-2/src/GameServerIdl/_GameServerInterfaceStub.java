package GameServerIdl;

/**
 * Interface definition: GameServerInterface.
 * 
 * @author OpenORB Compiler
 */
public class _GameServerInterfaceStub extends org.omg.CORBA.portable.ObjectImpl
        implements GameServerInterface
{
    static final String[] _ids_list =
    {
        "IDL:GameServerIdl/GameServerInterface:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = GameServerIdl.GameServerInterfaceOperations.class;

    /**
     * Operation checkMethod
     */
    public void checkMethod(String param1, org.omg.CORBA.StringHolder reply)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("checkMethod",true);
                    _output.write_string(param1);
                    _input = this._invoke(_output);
                    reply.value = _input.read_string();
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("checkMethod",_opsClass);
                if (_so == null)
                   continue;
                GameServerIdl.GameServerInterfaceOperations _self = (GameServerIdl.GameServerInterfaceOperations) _so.servant;
                try
                {
                    _self.checkMethod( param1,  reply);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation createPlayerAccount
     */
    public void createPlayerAccount(String firstname, String lastname, int age, String username, String password, String ipaddress, org.omg.CORBA.StringHolder success_status)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("createPlayerAccount",true);
                    _output.write_string(firstname);
                    _output.write_string(lastname);
                    _output.write_long(age);
                    _output.write_string(username);
                    _output.write_string(password);
                    _output.write_string(ipaddress);
                    _input = this._invoke(_output);
                    success_status.value = _input.read_string();
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("createPlayerAccount",_opsClass);
                if (_so == null)
                   continue;
                GameServerIdl.GameServerInterfaceOperations _self = (GameServerIdl.GameServerInterfaceOperations) _so.servant;
                try
                {
                    _self.createPlayerAccount( firstname,  lastname,  age,  username,  password,  ipaddress,  success_status);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation playerSignIn
     */
    public void playerSignIn(String username, String password, String ipaddress, org.omg.CORBA.StringHolder success_status)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("playerSignIn",true);
                    _output.write_string(username);
                    _output.write_string(password);
                    _output.write_string(ipaddress);
                    _input = this._invoke(_output);
                    success_status.value = _input.read_string();
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("playerSignIn",_opsClass);
                if (_so == null)
                   continue;
                GameServerIdl.GameServerInterfaceOperations _self = (GameServerIdl.GameServerInterfaceOperations) _so.servant;
                try
                {
                    _self.playerSignIn( username,  password,  ipaddress,  success_status);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation playerSignOut
     */
    public void playerSignOut(String username, String ipaddress, org.omg.CORBA.StringHolder success_status)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("playerSignOut",true);
                    _output.write_string(username);
                    _output.write_string(ipaddress);
                    _input = this._invoke(_output);
                    success_status.value = _input.read_string();
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("playerSignOut",_opsClass);
                if (_so == null)
                   continue;
                GameServerIdl.GameServerInterfaceOperations _self = (GameServerIdl.GameServerInterfaceOperations) _so.servant;
                try
                {
                    _self.playerSignOut( username,  ipaddress,  success_status);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation getPlayerStatus
     */
    public void getPlayerStatus(String username, String ipaddress, org.omg.CORBA.StringHolder success_status)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getPlayerStatus",true);
                    _output.write_string(username);
                    _output.write_string(ipaddress);
                    _input = this._invoke(_output);
                    success_status.value = _input.read_string();
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getPlayerStatus",_opsClass);
                if (_so == null)
                   continue;
                GameServerIdl.GameServerInterfaceOperations _self = (GameServerIdl.GameServerInterfaceOperations) _so.servant;
                try
                {
                    _self.getPlayerStatus( username,  ipaddress,  success_status);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation suspendAccount
     */
    public void suspendAccount(String adminusername, String adminpassword, String adminipaddress, String usernametosuspend, org.omg.CORBA.StringHolder success_status)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("suspendAccount",true);
                    _output.write_string(adminusername);
                    _output.write_string(adminpassword);
                    _output.write_string(adminipaddress);
                    _output.write_string(usernametosuspend);
                    _input = this._invoke(_output);
                    success_status.value = _input.read_string();
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("suspendAccount",_opsClass);
                if (_so == null)
                   continue;
                GameServerIdl.GameServerInterfaceOperations _self = (GameServerIdl.GameServerInterfaceOperations) _so.servant;
                try
                {
                    _self.suspendAccount( adminusername,  adminpassword,  adminipaddress,  usernametosuspend,  success_status);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation transferAccount
     */
    public void transferAccount(String username, String password, String oldipaddress, String newipaddress, org.omg.CORBA.StringHolder success_status)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("transferAccount",true);
                    _output.write_string(username);
                    _output.write_string(password);
                    _output.write_string(oldipaddress);
                    _output.write_string(newipaddress);
                    _input = this._invoke(_output);
                    success_status.value = _input.read_string();
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("transferAccount",_opsClass);
                if (_so == null)
                   continue;
                GameServerIdl.GameServerInterfaceOperations _self = (GameServerIdl.GameServerInterfaceOperations) _so.servant;
                try
                {
                    _self.transferAccount( username,  password,  oldipaddress,  newipaddress,  success_status);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
