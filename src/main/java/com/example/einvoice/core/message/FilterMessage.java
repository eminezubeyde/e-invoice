package com.example.einvoice.core.message;

    public enum FilterMessage {
        NOT_FOUND("filterMessage.NOT_FOUND"),
        NOT_FOUND_DATE("filterMessage.NOT_FOUND_DATE"),
        SUCCESSFUL("filterMessage.SUCCESSFUL"),
        PAGE_COUNT_INVALID("filterMessage.PAGE_COUNT_INVALID"),
        BAD_REQUEST("filterMessage.BAD_REQUEST"),
        INVALID_DATE("filterMessage.INVALID_DATE");

        private final String key;

        FilterMessage(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

