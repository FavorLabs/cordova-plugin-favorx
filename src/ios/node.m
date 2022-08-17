    /********* node.m Cordova Plugin Implementation *******/
// cordova platform add ios
#import "node.h"
#import "FavorX.framework/Headers/FavorX.h"

@implementation node

static MobileNode *mobileNode = nil;

- (void)getVersion:(CDVInvokedUrlCommand*)command
{
    NSString *version = MobileVersion();
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:version];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}
- (void)start:(CDVInvokedUrlCommand*)command
{
    // Check command.arguments here.
    [self.commandDelegate runInBackground:^{
        NSDictionary* paramsDic = [command.arguments objectAtIndex:0];
        if (paramsDic.count > 0) {

            MobileOptions* options = [[MobileOptions alloc] init];
            // api setting
            options.apiPort = [[paramsDic objectForKey:@"APIPort"] intValue];
            options.debugAPIPort = [[paramsDic objectForKey:@"DebugAPIPort"] intValue];
            options.websocketPort = [[paramsDic objectForKey:@"WebsocketPort"] intValue];
            options.enableDebugAPI = [[paramsDic objectForKey:@"EnableDebugAPI"] boolValue];
            // p2p setup
            options.networkID = [[paramsDic objectForKey:@"NetworkID"] intValue];
            options.p2PPort = [[paramsDic objectForKey:@"P2PPort"] intValue];
            options.welcomeMessage = [paramsDic objectForKey:@"WelcomeMessage"];
            // kademlia
            options.binMaxPeers = [[paramsDic objectForKey:@"BinMaxPeers"] intValue];
            options.lightMaxPeers = [[paramsDic objectForKey:@"LightMaxPeers"] intValue];
            // cache size
            options.cacheCapacity = [[paramsDic objectForKey:@"CacheCapacity"] intValue];
            //node bootstrap
            options.bootNodes = [paramsDic objectForKey:@"BootNodes"];
            options.enableDevNode = [[paramsDic objectForKey:@"EnableDevNode"] boolValue];
            options.enableFullNode = [[paramsDic objectForKey:@"EnableFullNode"] boolValue];
            // chain setting
            options.chainEndpoint = [paramsDic objectForKey:@"ChainEndpoint"];
            options.oracleContract = [paramsDic objectForKey:@"OracleContract"];
            // domain resolver
            options.resolverOptions = [paramsDic objectForKey:@"ResolverOptions"];
            // security
            options.password = [paramsDic objectForKey:@"Password"];
            options.dataPath = [paramsDic objectForKey:@"DataPath"];
            // leveldb opts
            options.blockCacheCapacity = [[paramsDic objectForKey:@"BlockCacheCapacity"] intValue];
            options.openFilesLimit = [[paramsDic objectForKey:@"OpenFilesLimit"] intValue];
            options.writeBufferSize = [[paramsDic objectForKey:@"WriteBufferSize"] intValue];
            options.disableSeeksCompaction = [[paramsDic objectForKey:@"DisableSeeksCompaction"] boolValue];
            // misc
            options.verbosity = [paramsDic objectForKey:@"Verbosity"];

            // 获取Documents目录
            //NSString *docPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
            //options.dataPath = docPath;
            NSError* error = nil;
            mobileNode = MobileNewNode(options, &error);
            if(error == nil){
                 // Some blocking logic...
                 CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[paramsDic objectForKey:@"APIPort"]];
                // The sendPluginResult method is thread-safe.
                 [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
              }else{
                 CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
                 [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
              }
        }else {
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }

    }];
}
@end