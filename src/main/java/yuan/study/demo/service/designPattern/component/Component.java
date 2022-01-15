package yuan.study.demo.service.designPattern.component;

public interface Component {

    /**
     * 增加节点
     */
    void add(Component component);

    /**
     * 移除节点
     */
    void remove(Component component);

    /**
     * 遍历节点
     * @param i 层级
     */
    void display(int i);
}