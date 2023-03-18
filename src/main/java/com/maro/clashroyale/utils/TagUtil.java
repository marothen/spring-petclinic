package com.maro.clashroyale.utils;

import com.maro.clashroyale.Constants;

public abstract class TagUtil {


    public static String formatTag(String clanTag) {
        String result = clanTag!=null?clanTag: Constants.CLAN_ID;
        if(result.startsWith("#")) {
            result = result.substring(1);
        }
        if(!result.startsWith(Constants.TAG_PREFIX)) {
            result = Constants.TAG_PREFIX + result;
        }
        return result;
    }


    public static String unformatTag(String clanTag) {
        String result = clanTag!=null?clanTag: Constants.CLAN_ID;
        if(result.startsWith("%23")) {
            result = result.substring(3);
        }
        return result;
    }
}
