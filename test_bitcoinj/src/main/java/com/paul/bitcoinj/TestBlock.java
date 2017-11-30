package com.paul.bitcoinj;

import com.paul.bitcoinj.utils.Log;
import org.bitcoinj.core.*;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.utils.BriefLogFormatter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestBlock {
    public static void main(String[] args) {
        BriefLogFormatter.init();
        NetworkParameters params = TestNet3Params.get();

        BlockStore blockStore = new MemoryBlockStore(params);

        try {
            BlockChain chain = new BlockChain(params, blockStore);
            PeerGroup peerGroup = new PeerGroup(params, chain);
            peerGroup.start();
            PeerAddress addr = new PeerAddress(InetAddress.getByName("localhost"), params.getPort());
            peerGroup.addAddress(addr);

            Peer peer = peerGroup.getConnectedPeers().get(0);

            Sha256Hash blockHash = Sha256Hash.wrap("00000007199508e34a9ff81e6ec0c477a4cccff2a4767a8eee39c11db367b008");
            Future<Block> future = peer.getBlock(blockHash);

            Log.p("Waiting for node to send us the requested block: " + blockHash);
            Block block = future.get();
            System.out.println(block);
            peerGroup.stopAsync();
        } catch (BlockStoreException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
