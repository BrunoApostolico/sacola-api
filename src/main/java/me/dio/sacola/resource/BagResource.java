package me.dio.sacola.resource;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.dio.sacola.model.Bag;
import me.dio.sacola.model.Item;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.BagService;
import org.springframework.web.bind.annotation.*;

@Api(value = "/ifood-devweek/sacolas")
@RestController
@RequestMapping(value = "/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class BagResource {

    private final BagService bagService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto) {
        return bagService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Bag verBag(@PathVariable("id") Long id){
        return bagService.verBag(id);
    }

    @PatchMapping("/fecharSacola/{bagId}")
    public Bag fecharBag(@PathVariable("bagId") Long bagId, @RequestParam("formaPagamento") int formaPagamento){
        return bagService.fecharBag(bagId, formaPagamento);
    }


}
