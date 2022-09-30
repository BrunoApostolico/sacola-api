package me.dio.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.enumeration.FormaPagamento;
import me.dio.sacola.model.Bag;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Product;
import me.dio.sacola.model.Restaurant;
import me.dio.sacola.repository.BagRepository;
import me.dio.sacola.repository.ItemRepository;
import me.dio.sacola.repository.ProductRepository;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.BagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {
    private final BagRepository bagRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Bag bag = verBag(itemDto.getSacolaId());

        if (bag.isFechada()) {
            throw new RuntimeException("Esta sacola esta fechada.");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(bag)
                .produto(productRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Produto não encontrado.");
                        }))
                .build();

        List<Item> itensDaSacola = bag.getItens();
        if (itensDaSacola.isEmpty()){
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurant restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurant restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();

            if(restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
                itensDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Não é possivel adicionar produtos de restaurantes diferentes.");
            }
        }

        List<Double> valorDosItens = new ArrayList<>();

        for (Item itemDaSacola: itensDaSacola){
            double valorTotalItem =
                itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        double valorTotalSacola = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();

        bag.setValorTotal(valorTotalSacola);
        bagRepository.save(bag);
        return itemRepository.save(itemParaSerInserido);
    }

    @Override
    public Bag verBag(Long id) {
        return bagRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Essa sacola não existe");
                }
        );
    }

    @Override
    public Bag fecharBag(Long id, int numeroformaPagamento) {
        Bag bag = verBag(id);

        if (bag.getItens().isEmpty()){
            throw new RuntimeException("Incluir Item Na Sacola!");
        }

        FormaPagamento formaPagamento =
                numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        bag.setFormaPagamento(formaPagamento);
        bag.setFechada(true);
        return bagRepository.save(bag);
    }
}
