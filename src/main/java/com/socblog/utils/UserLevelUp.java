package com.socblog.utils;

import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.enums.ELevelUpOptions;

public class UserLevelUp {

    public static User levelUp(User user, ELevelUpOptions eLevelUpOptions){
        switch (eLevelUpOptions){
            case LEVEL_UP_BY_POST:

                break;
            case LEVEL_UP_BY_FOLLOW:
                break;
            case LEVEL_UP_BY_READ_POSTS:
                break;
        }
        return null;
    }
    public static User levelUpByPost(User user, Post post){
        if(user != null && post !=null){
           double numberOfChars = (post.getText().toCharArray().length) * 0.025;
            user.setCurrentExperience(user.getCurrentExperience() + (numberOfChars * ELevelUpOptions.LEVEL_UP_BY_POST.getCoefficient()));
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
        return user;
    }


    private static double upCurrentExperience(User user){
        return (user.getExperienceToNextLevel() + 50)*1.2;
    }


}
