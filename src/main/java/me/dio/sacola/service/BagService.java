package me.dio.sacola.service;

import me.dio.sacola.model.Bag;
import me.dio.sacola.model.Item;
import me.dio.sacola.resource.dto.ItemDto;

public interface BagService {
    Item incluirItemNaSacola(ItemDto itemDto);
    Bag verBag(Long id);
    Bag fecharBag(Long id, int formaPagamento);
}
