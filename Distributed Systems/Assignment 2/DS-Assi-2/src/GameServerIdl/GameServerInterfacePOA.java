package GameServerIdl;

/**
 * Interface definition: GameServerInterface.
 * 
 * @author OpenORB Compiler
 */
public abstract class GameServerInterfacePOA extends org.omg.PortableServer.Servant
        implements GameServerInterfaceOperations, org.omg.CORBA.portable.InvokeHandler
{
    public GameServerInterface _this()
    {
        return GameServerInterfaceHelper.narrow(_this_object());
    }

    public GameServerInterface _this(org.omg.CORBA.ORB orb)
    {
        return GameServerInterfaceHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:GameServerIdl/GameServerInterface:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("checkMethod")) {
                return _invoke_checkMethod(_is, handler);
        } else if (opName.equals("createPlayerAccount")) {
                return _invoke_createPlayerAccount(_is, handler);
        } else if (opName.equals("getPlayerStatus")) {
                return _invoke_getPlayerStatus(_is, handler);
        } else if (opName.equals("playerSignIn")) {
                return _invoke_playerSignIn(_is, handler);
        } else if (opName.equals("playerSignOut")) {
                return _invoke_playerSignOut(_is, handler);
        } else if (opName.equals("suspendAccount")) {
                return _invoke_suspendAccount(_is, handler);
        } else if (opName.equals("transferAccount")) {
                return _invoke_transferAccount(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_checkMethod(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        org.omg.CORBA.StringHolder arg1_out = new org.omg.CORBA.StringHolder();

        checkMethod(arg0_in, arg1_out);

        _output = handler.createReply();

        _output.write_string(arg1_out.value);
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_createPlayerAccount(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        int arg2_in = _is.read_long();
        String arg3_in = _is.read_string();
        String arg4_in = _is.read_string();
        String arg5_in = _is.read_string();
        org.omg.CORBA.StringHolder arg6_out = new org.omg.CORBA.StringHolder();

        createPlayerAccount(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in, arg5_in, arg6_out);

        _output = handler.createReply();

        _output.write_string(arg6_out.value);
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_playerSignIn(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        org.omg.CORBA.StringHolder arg3_out = new org.omg.CORBA.StringHolder();

        playerSignIn(arg0_in, arg1_in, arg2_in, arg3_out);

        _output = handler.createReply();

        _output.write_string(arg3_out.value);
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_playerSignOut(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        org.omg.CORBA.StringHolder arg2_out = new org.omg.CORBA.StringHolder();

        playerSignOut(arg0_in, arg1_in, arg2_out);

        _output = handler.createReply();

        _output.write_string(arg2_out.value);
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_getPlayerStatus(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        org.omg.CORBA.StringHolder arg2_out = new org.omg.CORBA.StringHolder();

        getPlayerStatus(arg0_in, arg1_in, arg2_out);

        _output = handler.createReply();

        _output.write_string(arg2_out.value);
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_suspendAccount(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();
        org.omg.CORBA.StringHolder arg4_out = new org.omg.CORBA.StringHolder();

        suspendAccount(arg0_in, arg1_in, arg2_in, arg3_in, arg4_out);

        _output = handler.createReply();

        _output.write_string(arg4_out.value);
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_transferAccount(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();
        org.omg.CORBA.StringHolder arg4_out = new org.omg.CORBA.StringHolder();

        transferAccount(arg0_in, arg1_in, arg2_in, arg3_in, arg4_out);

        _output = handler.createReply();

        _output.write_string(arg4_out.value);
        return _output;
    }

}
