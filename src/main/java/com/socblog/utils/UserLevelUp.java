package com.socblog.utils;

import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.enums.ELevelUpOptions;

public class UserLevelUp {


    public User levelUpByPost(User user, Post post){
        if(user != null && post !=null){
           double numberOfChars = (post.getText().toCharArray().length) * 0.025;
            user.setCurrentExperience(user.getCurrentExperience() + (numberOfChars * ELevelUpOptions.LEVEL_UP_BY_POST.getCoefficient()));
            upLvl(user);
        }
        return user;
    }
    public User levelUpByFollow(User user){
        if(user != null){
            user.setCurrentExperience(user.getCurrentExperience() + ( 20 * ELevelUpOptions.LEVEL_UP_BY_POST.getCoefficient()));
            upLvl(user);
        }
        return user;
    }

    private void upLvl(User user) {
        if(user.getCurrentExperience() >= user.getExperienceToNextLevel()){
            double remainderOfExp = user.getCurrentExperience() - user.getExperienceToNextLevel();
            if(remainderOfExp >= 0) {
                while (remainderOfExp >= 0) {
                    user.setCurrentExperience(remainderOfExp);
                    user.setExperienceToNextLevel(upCurrentExperience(user));
                    user.setAccountLvl(user.getAccountLvl() + 1);
                    remainderOfExp -= user.getExperienceToNextLevel();
                }
            }else{
                user.setAccountLvl(user.getAccountLvl() + 1);
            }
        }
    }


    private double upCurrentExperience(User user){
        return (user.getExperienceToNextLevel() + 50)*1.2;
    }


}
