package com.paul.bitcoinj;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.TestNet3Params;


public class TestWallet {
    public static void main(String[] args) {
        testWalletAddress();
    }

    public static void testWalletAddress() {
        System.out.println("address base58 code : " + getWalletAddress());
    }

    //以下是工具方法

    /**
     * 获取地址编码
     * @return
     */
    public static String getWalletAddress() {
        ECKey ceKey = new ECKey();
        NetworkParameters params = TestNet3Params.get();
        return ceKey.toAddress(params).toBase58(); // base58编码后的地址
    }
}
