package com.example.backend.services.impl;

import com.example.backend.repository.WalletRepository;
import com.example.backend.services.WalletService;
import com.example.backend.entities.UserMeta;
import com.example.backend.entities.Wallet;
import com.example.backend.repository.UserMetaRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WalletServiceImpl implements WalletService{

    private final WalletRepository walletInfo;
    private final UserMetaRepository userMeta;

    public WalletServiceImpl(WalletRepository walletInfo, UserMetaRepository userMeta){
        this.walletInfo = walletInfo;
        this.userMeta = userMeta;
    }

    public double checkBalance(int userId){
        UserMeta walletBalanceById = userMeta.findById(userId);
        double myBalance = 0.0;
        if (walletBalanceById != null){
            myBalance = walletBalanceById.getWallet_balance();
        }
        return myBalance;
    }

    public void addMoney(int userId, double amount, Date date){
        // Retrieving the  user's wallet info.
        UserMeta userInfo = userMeta.findById(userId);
        Wallet amountInfo = new Wallet();
        if (userInfo != null){
           
            double updatedBalance = userInfo.getWallet_balance() + amount;
            userInfo.setWallet_balance(updatedBalance); // updating the wallet_balance in the user_meta.
            amountInfo.setAmount_Added(amount);
            amountInfo.setTransaction_Date(date);
            // amountInfo.setUser(userInfo);
            amountInfo.setPaymnent_Method_Reference("N.A.N");

            //Updating the  Amount_Added in the customer_wallet_history table. 
            userMeta.save(userInfo);
            walletInfo.save(amountInfo);
            // Saving the Transaction.
        } else {
            System.out.println("OOPS: Wallet Info not found for the pertaining user: " + userId);
        }
    }

    // public void spendMoney(int payerId, int receiverId, double amount){
    //     UserMeta payerInfo = userMeta.findById(payerId);
    //     UserMeta receiverInfo = userMeta.findById(receiverId);

    //     if(payerInfo!= null && receiverInfo != null){
    //         double updatePayerBalance = payerInfo.getWallet_balance() - amount;
    //         payerInfo.setWallet_balance(updatePayerBalance);
    //         // Updating the sender side for the payment. 

    //         double updateReceiverBalance = receiverInfo.getWallet_balance() + amount;
    //         receiverInfo.setWallet_balance(updateReceiverBalance);
    //         // Updating the receiver side after the deduction from the payer side.
    //         userMeta.save(payerInfo);
    //         userMeta.save(receiverInfo);
    //         // Saving the transaction
    //     } else {
    //         System.out.println("OOPS: Wallet Info not found for either Payer or Receiver");
    //     }
    // }
}
