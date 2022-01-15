package yuan.study.demo.service.designPattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptContext {

    /**
     * 策略
     */
    private Strategy strategy;

    /**
     * 执行加密
     */
    public void encrypt() {
        this.strategy.encrypt();
    }
}