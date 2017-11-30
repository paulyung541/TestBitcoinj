package com.paul.bitcoinj;

import com.paul.bitcoinj.utils.Log;
import com.subgraph.orchid.encoders.Hex;
import org.bitcoinj.core.*;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;


import java.io.File;
import java.io.IOException;


public class TestWallet {
    public static void main(String[] args) {
//        testWalletAddress();
        recoverWalletFromFile();
    }

    //以下是单元测试方法

    /**
     * 测试获取地址编码
     */
    public static void testWalletAddress() {
        Log.p("address base58 code : " + getWalletAddress());
    }

    /**
     * 创建钱包，添加密钥对(有钱包文件就加载，没有就创建并加载)
     */
    public static void recoverWalletFromFile() {
        NetworkParameters netParams = TestNet3Params.get();
        final WalletAppKit walletAppKit = new WalletAppKit(netParams, new File("test.wallet"), "") {
            @Override
            protected void onSetupCompleted() {
                //如果没有key，则可以添加
//                Log.p("进入：onSetupCompleted");
//                Log.p("key chain group size = " + wallet().getKeyChainGroupSize());
//                wallet().importKey(new ECKey());
//                Log.p("添加 key");
            }
        };

        if (null == netParams) {
            walletAppKit.connectToLocalHost();
        }

        walletAppKit.startAsync();
        walletAppKit.awaitRunning();

        ECKey firstKey = walletAppKit.wallet().getImportedKeys().get(0);

        Log.p("the key : " + firstKey);

        Log.p("wallet : " + walletAppKit.wallet());

//        Log.p("address decode = " + new String(Hex.encode(Base58.decode("my6EpvH8vqK1KoAgMfVLDjNqEi8cr6iFMf"))));

        if (walletAppKit.wallet().isPubKeyHashMine(firstKey.getPubKeyHash())) {
            Log.p("that's my key.");
        } else {
            Log.p("not my key!");
        }

    }

    //以下是工具方法

    /**
     * 获取地址编码
     *
     * @return
     */
    public static String getWalletAddress() {
        ECKey ceKey = new ECKey();
        NetworkParameters params = TestNet3Params.get();
        return ceKey.toAddress(params).toBase58(); // base58编码后的地址
    }
}
