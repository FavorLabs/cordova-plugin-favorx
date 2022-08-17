var exec = require('cordova/exec');


exports.getVersion = function (success, error) {
    exec(success, error, "node", 'getVersion');
}

exports.start = function (success, error) {

    exec(success, error, "node", 'start', [{
        APIPort: 1633,
        DebugAPIPort: 1635,
        WebsocketPort: 1639,
        EnableDebugAPI: true,
        NetworkID: 19,
        P2PPort: 1634,
        WelcomeMessage: "",
        BinMaxPeers: 50,
        LightMaxPeers: 100,
        CacheCapacity: 4 * 1024,
        BootNodes:
            "/ip4/107.167.2.7/tcp/1809/p2p/12D3KooWEw5bXwg4ho63XSJCtmYdgXbVGUKrf1Uhpg3RbogLswxt",
        EnableDevNode: false,
        EnableFullNode: false,
        ChainEndpoint: "https://polygon-testnet.public.blastapi.io",
        OracleContract: "0x21aC8FE412Fd058eD29a67a69c81EF08fA34f443",
        ResolverOptions:
            `A959836a03abbc7bF7EFAD8F9422456150bFE567@https://polygon-testnet.public.blastapi.io`,
        Password: "123456",
        // eslint-disable-next-line no-undef
        DataPath: cordova.file.dataDirectory.substring(7),
        BlockCacheCapacity: 8 * 1024 * 1024,
        OpenFilesLimit: 200,
        WriteBufferSize: 4 * 1024 * 1024,
        DisableSeeksCompaction: false,
        Verbosity: "trace",
    },]);
}
