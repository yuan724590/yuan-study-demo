package yuan.study.demo.service.designPattern.state;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StateContext {

    /**
     * 状态
     */
    private State state;
}