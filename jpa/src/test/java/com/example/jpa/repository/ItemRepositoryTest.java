package com.example.jpa.repository;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Board;
import com.example.jpa.entity.Item;
import com.example.jpa.entity.ItemSellStatus;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private itemRepository itemRepository;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Item item = Item.builder()
                    .itemNm("운동화" + i)
                    .price(95000 * i)
                    .stockNumber(30)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();

            itemRepository.save(item);
        });
    }

    @Test
    public void readTest() {
        System.out.println(itemRepository.findById(2L));

        System.out.println("------------------");
        itemRepository.findAll().forEach(item -> System.out.println(item));
    }

    @Test
    public void updateTest() {
        Optional<Item> result = itemRepository.findById(5L);

        result.ifPresent(item -> {
            item.setItemNm("티셔츠");
            item.setPrice(8421548);

            System.out.println(itemRepository.save(item));
        });
    }

    @Test
    public void deleteTest() {
        Optional<Item> result = itemRepository.findById(8L);

        itemRepository.delete(result.get());
        System.out.println("item 삭제 : " + itemRepository.findById(8L));
    }
}
