package yuan.study.demo.service.designPattern.component;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class Composite implements Component {

    /**
     * 节点名字
     */
    private String name;

    /**
     * 一个子对象集合用来存储其下的枝节点和叶节点
     */
    private List<Component> componentList;

    public Composite(String name){

        this.name = name;
    }

    @Override
    public void add(Component component) {
        componentList = CollectionUtils.isEmpty(componentList) ? new ArrayList<>() : componentList;
        componentList.add(component);
    }

    @Override
    public void remove(Component component) {
        if(CollectionUtils.isEmpty(componentList)){
            return;
        }
        componentList.remove(component);
    }

    @Override
    public void display(int i) {
        //显示枝节点名称
        for(int m = 0; m < i; m++){
            System.out.print("-");
        }
        System.out.println(getName());
        i++;
        //对子节点进行遍历
        if(CollectionUtils.isNotEmpty(componentList)){
            for (Component component : componentList) {
                component.display(i);
            }
        }
    }
}