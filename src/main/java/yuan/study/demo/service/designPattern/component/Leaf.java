package yuan.study.demo.service.designPattern.component;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Leaf implements Component{

    /**
     * 节点名字
     */
    private String name;

    /**
     * 叶节点没有子节点，实现add和remove没有意义，但是这样可以消除叶节点和枝节点的层次差别，使他们具备完全一致的接口
     */
    @Override
    public void add(Component component) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void display(int i) {
        for(int m = 0; m < i; m++){
            System.out.print("-");
        }
        System.out.println(getName());
    }
}