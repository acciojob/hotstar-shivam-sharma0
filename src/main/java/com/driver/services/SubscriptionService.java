package com.driver.services;


import com.driver.EntryDto.SubscriptionEntryDto;
import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.repository.SubscriptionRepository;
import com.driver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    public Integer buySubscription(SubscriptionEntryDto subscriptionEntryDto){

        //Save The subscription Object into the Db and return the total Amount that user has to pay
        Integer numberOfScreen=subscriptionEntryDto.getNoOfScreensRequired();
        Integer totalAmount=0;

        User user=userRepository.findById(subscriptionEntryDto.getUserId()).get();


        Subscription subscription= new Subscription();

        if(subscriptionEntryDto.getSubscriptionType().equals(SubscriptionType.BASIC)){
            Integer amountWithNoOfScreenAmount=500+(200*numberOfScreen);
            totalAmount=amountWithNoOfScreenAmount;
            subscription.setSubscriptionType(SubscriptionType.BASIC);

        } else if (subscriptionEntryDto.getSubscriptionType().equals(SubscriptionType.PRO)) {
            Integer amountWithNoOfScreenAmount=800+(250*numberOfScreen);
            totalAmount=amountWithNoOfScreenAmount;
            subscription.setSubscriptionType(SubscriptionType.PRO);

        }else {
            Integer amountWithNoOfScreenAmount=1000+(350*numberOfScreen);
            totalAmount=amountWithNoOfScreenAmount;
            subscription.setSubscriptionType(SubscriptionType.ELITE);
        }

        subscription.setUser(user);
        subscription.setTotalAmountPaid(totalAmount);
        subscription.setNoOfScreensSubscribed(numberOfScreen);
        subscription=subscriptionRepository.save(subscription);

        user.setSubscription(subscription);

        return totalAmount;
    }

    public Integer upgradeSubscription(Integer userId)throws Exception{

        //If you are already at an ElITE subscription : then throw Exception ("Already the best Subscription")
        //In all other cases just try to upgrade the subscription and tell the difference of price that user has to pay
        //update the subscription in the repository

        return null;
    }

    public Integer calculateTotalRevenueOfHotstar(){

        //We need to find out total Revenue of hotstar : from all the subscriptions combined
        //Hint is to use findAll function from the SubscriptionDb

        return null;
    }

}
