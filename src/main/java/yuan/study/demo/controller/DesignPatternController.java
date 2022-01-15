package yuan.study.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.DesignPatternService;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class DesignPatternController {

    @Resource
    private DesignPatternService designPatternService;

    // -------------------------------------- 创建型模式 -----------------------------------------------------
    /**
     * 单例模式
     */
    @GetMapping(value = "/singleton/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String singleton() {
        return designPatternService.singleton();
    }

    /**
     * 简单工厂模式
     */
    @GetMapping(value = "/simple/factory/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String simpleFactory() {
        return designPatternService.simpleFactory();
    }

    /**
     * 抽象工厂模式
     */
    @GetMapping(value = "/abstract/factory/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String abstractFactory() {
        return designPatternService.abstractFactory();
    }

    /**
     * 建造者模式
     */
    @GetMapping(value = "/builder/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String builder() {
        return designPatternService.builder();
    }

    /**
     * 原型模式
     */
    @GetMapping(value = "/prototype/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String prototype() {
        return designPatternService.prototype();
    }

    // -------------------------------------- 结构型模式 -----------------------------------------------------

    /**
     * 适配器模式
     */
    @GetMapping(value = "/adapter/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String adapter() {
        return designPatternService.adapter();
    }

    /**
     * 组合模式
     */
    @GetMapping(value = "/component/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String component() {
        return designPatternService.component();
    }

    /**
     * 装饰器模式
     */
    @GetMapping(value = "/decorator/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String decorator() {
        return designPatternService.decorator();
    }

    /**
     * 代理模式
     */
    @GetMapping(value = "/proxy/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String proxy() {
        return designPatternService.proxy();
    }

    /**
     * 享元模式
     */
    @GetMapping(value = "/flyweight/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String flyweight() {
        return designPatternService.flyweight();
    }

    /**
     * 外观模式
     */
    @GetMapping(value = "/facade/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String facade() {
        return designPatternService.facade();
    }

    /**
     * 桥接模式
     */
    @GetMapping(value = "/bridge/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String bridge() {
        return designPatternService.bridge();
    }

    /**
     * 模板模式
     */
    @GetMapping(value = "/template/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String template() {
        return designPatternService.template();
    }

    /**
     * 解释器模式
     */
    @GetMapping(value = "/interpreter/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String interpreter() {
        return designPatternService.interpreter();
    }

    /**
     * 策略模式
     */
    @GetMapping(value = "/strategy/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String strategy() {
        return designPatternService.strategy();
    }

    /**
     * 状态模式
     */
    @GetMapping(value = "/state/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String state() {
        return designPatternService.state();
    }

    /**
     * 观察者模式
     */
    @GetMapping(value = "/observer/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String observer() {
        return designPatternService.observer();
    }

    /**
     * 备忘录模式
     */
    @GetMapping(value = "/memento/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String memento() {
        return designPatternService.memento();
    }

    /**
     * 中介者模式
     */
    @GetMapping(value = "/mediator/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String mediator() {
        return designPatternService.mediator();
    }

    /**
     * 命令模式
     */
    @GetMapping(value = "/command/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String command() {
        return designPatternService.command();
    }

    /**
     * 访问者模式
     */
    @GetMapping(value = "/visitor/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String visitor() {
        return designPatternService.visitor();
    }

    /**
     * 责任链模式
     */
    @GetMapping(value = "/chainOfResponsibility/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String chainOfResponsibility() {
        return designPatternService.chainOfResponsibility();
    }

    /**
     * 迭代器模式
     */
    @GetMapping(value = "/iterator/pattern", produces = MediaType.APPLICATION_JSON_VALUE)
    public String iterator() {
        return designPatternService.iterator();
    }
}
