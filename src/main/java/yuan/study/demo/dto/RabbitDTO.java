package yuan.study.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RabbitDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeadLetterDTO {

        /**
         * 需要发送的内容
         */
        private String msg;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DelayDTO {

        /**
         * 需要发送的内容
         */
        private String msg;
    }
}
