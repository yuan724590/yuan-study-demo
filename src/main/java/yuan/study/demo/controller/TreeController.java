package yuan.study.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yuan.study.demo.service.TreeService;

import javax.annotation.Resource;

/**
 * @author yuan
 * @Date 2022/4/6 23:11
 */
@RestController
public class TreeController {

    @Resource
    private TreeService treeService;

    /**
     * 二叉查找树
     */
    @GetMapping(value = "/binaryFindTree")
    public void subjectTest() {
        treeService.binaryFindTree();
    }
}
