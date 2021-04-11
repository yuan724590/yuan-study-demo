package yuan.study.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DemoDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ThreadLocalDTO {

        /**
         * 需要缓存的值
         */
        private String value;
    }
}
