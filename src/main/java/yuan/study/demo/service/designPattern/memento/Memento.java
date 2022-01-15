package yuan.study.demo.service.designPattern.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Memento {

    /**
     * 备忘录的值
     */
    private String value;
}