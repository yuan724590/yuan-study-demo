package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.TreeService;

import javax.annotation.Resource;

@RestController
public class TreeController {

    @Resource
    private TreeService treeService;

    /**
     * 二叉查找树
     */
    @GetMapping(value = "/binary/find/tree")
    public void binaryFindTree() {
        treeService.binaryFindTree();
    }

    /**
     * 平衡二叉(查找)树
     */
    @GetMapping(value = "/binary/balance/tree")
    public void subjectTest() {
        treeService.binaryBalanceTree();
    }
}
