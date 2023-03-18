package com.maro.clashroyale;

public class Constants {
    public static final String CLAN_ID = "2VGRLRJQ";
    public static final String TAG_PREFIX = "%23";
    public static final String UNFORMATTED_TAG_PREFIX = "#";
    public static final String BASE_URL = "https://api.clashroyale.com/v1";
    //production
    public static final String AUTHORIZATION = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijc3NGFhNzhhLWJkODItNGZmMC1hODBhLTMyMGY2NGRjZWZjNiIsImlhdCI6MTY0ODk5NzU4OCwic3ViIjoiZGV2ZWxvcGVyLzYxMTlkZTBkLTg5MWEtMzg3Mi01ZTU5LTM5NzYzNjAwZTMzMyIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyI5NS4yMTcuNDMuMTcxIl0sInR5cGUiOiJjbGllbnQifV19.O9ZEOSvBhy-_66pTRsOv6gPM94ydjAKmImN2EXB3Sp4cWh8H5dr_mzRnAV1yUZ4AYqMwBoipvukiCdIJd4j0lw";
    //testing
    //public static final String AUTHORIZATION = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjU3YWNkMDZlLWE3MTMtNDY0My05NjQ5LWIwNDUxYzgyN2RmOSIsImlhdCI6MTY3OTEyMjk1NSwic3ViIjoiZGV2ZWxvcGVyLzYxMTlkZTBkLTg5MWEtMzg3Mi01ZTU5LTM5NzYzNjAwZTMzMyIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIyMTcuMjM0LjYwLjUxIl0sInR5cGUiOiJjbGllbnQifV19.Ps-C0Ujl5wJ9d9KL6fR2oJNb2Ia-LLv7lUO_BrKGi6BetJHG15lFxPAGxu7H4rRdnqpER2XoZ9D-1N3xKUpcdQ";
    public static final int MINIMUM_POINTS = 750;

    public static final int UPPER_POINTS = 2000;

    public static final int DOWNGRADE_POINTS = 1500;

    public static final int SAVE_WEEK_COUNT = -1;
    public static final int WEEKS_TO_UPGRADE = 0;

    public static final boolean DYNAMIC_MINIMUM_POINTS = false;
    public static final boolean DYNAMIC_UPPER_POINTS = false;
    public static final double UPPER_POINTS_FACTOR = 2;
    public static final double MINIMUM_POINTS_FACTOR = 0.5;
    public static final boolean ACTIVE_WEEK_MARGIN = true;
    public static final int AVTIVE_WEEK_COUNT = 10;
    public static final boolean IGNORE_DIFFERENT_RACE_MODES = true;
    public static final int MAX_DAYS_NOT_SEEN =7;
}
