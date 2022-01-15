package yuan.study.demo.service.designPattern.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Storage {

    /**
     * 备份的数据
     */
    private Memento memento;
}