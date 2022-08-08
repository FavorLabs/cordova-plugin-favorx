package node;


import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mobile.Mobile;
import mobile.Node;
import mobile.Options;

/**
 * This class echoes a string called from JavaScript.
 */
public class node extends CordovaPlugin {

    public static volatile Node node;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getVersion")) {
            this.getVersion(callbackContext);
            return true;
        } else if (action.equals("start")) {
            JSONObject json = args.getJSONObject(0);

            this.asyncStart(json, callbackContext);
            return true;
        }
        return false;
    }

    private void getVersion(CallbackContext callbackContext) {
        String version = Mobile.version();
        callbackContext.success(version);
    }


    private void asyncStart(JSONObject json, CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                start(json, callbackContext);
            }
        });
    }

    private void start(JSONObject json, CallbackContext callbackContext) {
        try {
            int APIPort = json.getInt("APIPort");
            int DebugAPIPort = json.getInt("DebugAPIPort");
            int WebsocketPort = json.getInt("WebsocketPort");
            boolean EnableDebugAPI = json.getBoolean("EnableDebugAPI");

            int NetworkID = json.getInt("NetworkID");
            int P2PPort = json.getInt("P2PPort");
            String WelcomeMessage = json.getString("WelcomeMessage");

            int BinMaxPeers = json.getInt("BinMaxPeers");
            int LightMaxPeers = json.getInt("LightMaxPeers");

            int CacheCapacity = json.getInt("CacheCapacity");

            String BootNodes = json.getString("BootNodes");
            boolean EnableDevNode = json.getBoolean("EnableDevNode");
            boolean EnableFullNode = json.getBoolean("EnableFullNode");

            String ChainEndpoint = json.getString("ChainEndpoint");
            String OracleContract = json.getString("OracleContract");

//             boolean EnableFlowStat = json.getBoolean("EnableFlowStat");
//             String FlowContract = json.getString("FlowContract");

            String ResolverOptions = json.getString("ResolverOptions");

            String Password = json.getString("Password");

            String DataPath = json.getString("DataPath");


            int BlockCacheCapacity = json.getInt("BlockCacheCapacity");
            int OpenFilesLimit = json.getInt("OpenFilesLimit");
            int WriteBufferSize = json.getInt("WriteBufferSize");
            boolean DisableSeeksCompaction = json.getBoolean("DisableSeeksCompaction");

            String Verbosity = json.getString("Verbosity");

            Options options = new Options();

            // api setting
            options.setAPIPort(APIPort);
            options.setDebugAPIPort(DebugAPIPort);
            options.setWebsocketPort(WebsocketPort);
            options.setEnableDebugAPI(EnableDebugAPI);

            // p2p setup
            options.setNetworkID(NetworkID);
            options.setP2PPort(P2PPort);
            options.setWelcomeMessage(WelcomeMessage);

            // kademlia
            options.setBinMaxPeers(BinMaxPeers);
            options.setLightMaxPeers(LightMaxPeers);

            // cache size
            options.setCacheCapacity(CacheCapacity);

            //node bootstrap
            options.setBootNodes(BootNodes);
            options.setEnableDevNode(EnableDevNode);
            options.setEnableFullNode(EnableFullNode);

            // chain setting
            options.setChainEndpoint(ChainEndpoint);
            options.setOracleContract(OracleContract);

//             // traffic stat
//             options.setEnableFlowStat(EnableFlowStat);
//             options.setFlowContract(FlowContract);

            // domain resolver
            options.setResolverOptions(ResolverOptions);

            // security
            options.setPassword(Password);

            options.setDataPath(DataPath);

            // leveldb opts
            options.setBlockCacheCapacity(BlockCacheCapacity);
            options.setOpenFilesLimit(OpenFilesLimit);
            options.setWriteBufferSize(WriteBufferSize);
            options.setDisableSeeksCompaction(DisableSeeksCompaction);

            // misc
            options.setVerbosity(Verbosity);

            node = Mobile.newNode(options);
            callbackContext.success(APIPort);

        } catch (Exception e) {
            System.out.println(e);
            callbackContext.error(e.getMessage());
        }
    }
}
