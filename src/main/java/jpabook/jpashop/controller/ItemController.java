package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("items/new")    //url 자체에 "items/1/new"처럼 요청하도록 해서 itmes/{item}/new 의 item을 숫자에 따라 book=1, movie=2, album=3처럼 구분해서
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm()); // 해당 객체를 넘겨주기 때문에 createItemForm.html에서 필드들을 인식하는 것!
        // 만약 item 중에서 고를 수 있도록 한다면 url에 book,item,
        return "items/createItemForm";
    }
    @PostMapping("items/new")
    public String createItem (@Valid ItemForm itemForm, BindingResult result) {
        if (result.hasErrors()) {
            return "items/createItemForm";
        }
        // 서비스 호출
        itemService.save(itemForm);
        return "redirect:/items";   // 책목록 페이지로 리다이렉트
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
    @PostMapping("/item/{itemId}/edit")
    public String update()
}
