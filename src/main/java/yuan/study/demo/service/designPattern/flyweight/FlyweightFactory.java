package yuan.study.demo.service.designPattern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {

    private static final Map<String, Gymnasium> map = new HashMap<>();

    /**
     * 获取建筑物信息
     */
    public static Gymnasium getArchitectureInfo(String name){
        return map.computeIfAbsent(name, value -> new Gymnasium(name));
    }
}