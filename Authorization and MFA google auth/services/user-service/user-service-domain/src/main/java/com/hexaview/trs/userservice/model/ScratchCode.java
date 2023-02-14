package com.hexaview.trs.userservice.model;

import com.hexaview.trs.common.util.UUIDUtil;

import java.util.List;


public class ScratchCode {

        private final Long id;

        private final String scratchCodeUuid;

        private final Long userIdFk;

        private final List<Integer> scratchCode;

        private ScratchCode(Builder builder) {
            this.id = builder.id;
            this.scratchCodeUuid = builder.scratchCodeUuid == null ? UUIDUtil.randomUUID() : builder.scratchCodeUuid;
            this.userIdFk = builder.userIdFk;
            this.scratchCode = builder.scratchCode;
        }

    public Long getId() {
        return id;
    }

    public String getScratchCodeUuid() {
        return scratchCodeUuid;
    }

    public Long getUserIdFk() {
        return userIdFk;
    }

    public List<Integer> getScratchCode() {
        return scratchCode;
    }

    public static final class Builder {
        private  Long id;

        private  String scratchCodeUuid;

        private  Long userIdFk;

        private List<Integer> scratchCode;

            public Builder(Long userIdFk, List<Integer> scratchCode) {
                this.userIdFk = userIdFk;
                this.scratchCode = scratchCode;
            }

            public Builder withId(Long id) {
                this.id = id;
                return this;
            }

            public Builder withScratchCodeUuid(String scratchCodeUuid) {
                this.scratchCodeUuid = scratchCodeUuid;
                return this;
            }


            public ScratchCode build() {
                return new ScratchCode(this);
            }
        }
}
