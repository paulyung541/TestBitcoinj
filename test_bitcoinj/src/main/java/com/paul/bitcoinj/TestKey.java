package com.paul.bitcoinj;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.TestNet3Params;

import java.security.SignatureException;

/**
 * ECKey 是专门用来生成密钥对的类
 */
public class TestKey {
    public static void main(String[] args) throws SignatureException {
//        testECKey();

    }

    //以下是单元测试方法

    public static void testECKey() {
        ECKey ceKey = new ECKey();
        System.out.println("ECKey is " + ceKey);
    }


    //以下是工具方法

    /**
     * 生成私钥字节数组
     * @return 私钥字节数组
     */
    public static byte[] createPrivateKeyForByteArray() {
        ECKey ceKey = new ECKey();
        return ceKey.getPrivKeyBytes();
    }

    /**
     * 生成字符串形式私钥
     * @return 私钥字符串
     */
    public static String createPrivateKeyForString() {
        return new String(createPrivateKeyForByteArray());
    }

    /**
     * 生成公钥字节数组
     * @return 公钥字节数组
     */
    public static byte[] createPublicKeyForByteArray() {
        ECKey ceKey = new ECKey();
        return ceKey.getPubKey();
    }

    /**
     * 生成字符串形式公钥
     * @return 公钥字符串
     */
    public static String createPublicKeyForString() {
        return new String(createPublicKeyForByteArray());
    }
}