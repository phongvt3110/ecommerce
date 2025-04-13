package com.org.shopping.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("test/")
public class TestController {
    @GetMapping("menu")
    public List<Menu> getMenus() {
        List<Menu> menuList = Arrays.asList(
                new Menu(1, "Home", null),
                new Menu(2, "Products", null),
                new Menu(3, "Laptops", 2),
                new Menu(4, "Phones", 2),
                new Menu(5, "Contact", null),
                new Menu(6, "abc", 3),
                new Menu(7, "adsfas", 3),
                new Menu(8, "Contact", 6),
                new Menu(9, "Contact", 6),
                new Menu(10, "Contact", 4),
                new Menu(11, "Contact", 4)
        );
        return buildMenuTree(groupMenuByParent(menuList), 0);
    }

    private Map<Integer, List<Menu>> groupMenuByParent(List<Menu> menus) {
        return menus.stream().collect(Collectors.groupingBy(menu -> menu.getParentId() == null ? 0 : menu.getParentId()));
    }
    private List<Menu> buildMenuTree(Map<Integer, List<Menu>> mapMenu, Integer parentId){
        return mapMenu.getOrDefault(parentId, new ArrayList<>()).stream()
                .peek(menu -> menu.setChildren(buildMenuTree(mapMenu, menu.getId())))
                .collect(Collectors.toList());
    }
}
