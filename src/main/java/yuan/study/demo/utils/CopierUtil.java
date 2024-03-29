package yuan.study.demo.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CopierUtil {

    /**
     * 单个对象属性拷贝
     */
    public static <T, M> T copyProperties(M source, Class<T> clazz){
        if (Objects.isNull( source) || Objects.isNull(clazz))
            throw new IllegalArgumentException();
        return copyProperties(source, clazz, null);
    }

    /**
     * 单个对象属性拷贝
     * @param source 源对象
     * @param clazz 目标对象CLass
     * @param copier copier
     * @param <T>目标对象类型
     * @param <M>源对象类型
     * @return 目标对象
     */
    private static <T, M> T copyProperties(M source, Class<T> clazz, BeanCopier copier){
        if (null == copier){
            copier = BeanCopier.create(source.getClass(),clazz,false);
        }
        T t = null;
        try {
            t = clazz.newInstance();
            copier.copy(source, t, null);
        }catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 列表对象拷贝
     */
    public static <T,M> List<T> copyobjects(List<M> sources, Class<T> clazz){
        if (Objects.isNull(sources) || Objects.isNull(clazz) || sources.isEmpty())
            throw new IllegalArgumentException();
        BeanCopier copier = BeanCopier.create(sources.get(0).getClass(),clazz,false);
        return Optional.of(sources)
                .orElse(new ArrayList<>())
                .stream().map(m ->copyProperties(m,clazz, copier)).collect(Collectors.toList());
    }
}
