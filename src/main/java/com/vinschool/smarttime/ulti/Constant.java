package com.vinschool.smarttime.ulti;

public interface Constant {
    public interface TYPE_TIME_LINE {
        public final int SO_DAU_BAI = 1;
        public final int TRONG_TRUA = 0;
    }

    public interface ROLE {
        public final String GIAO_VIEN = "ROLE_GIAOVIEN";
        public final String ADMIN = "ROLE_ADMIN";

    }

    public interface SATATUS {
        public final Boolean ACTIVE = true;
        public final Boolean NO_ACTIVE = false;
    }

    public interface SATATUS_TRAIN {
        public final Boolean YES = true;
        public final Boolean NO = false;
    }
}
