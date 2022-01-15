package yuan.study.demo.service.designPattern.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Original {

    /**
     * 源数据
     */
    private String value;

    /**
     * 创建备忘录
     */
    public Memento createMemento(){

        return new Memento(value);
    }

    /**
     * 恢复备忘录
     */
    public void restoreMemento(Memento memento){

        this.value = memento.getValue();
    }
}