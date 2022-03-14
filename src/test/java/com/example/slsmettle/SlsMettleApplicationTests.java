package com.example.slsmettle;

import com.example.slsmettle.domain.model.Item;
import com.example.slsmettle.domain.model.Type;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

//@SpringBootTest
class SlsMettleApplicationTests {

    @Test
    void checkItemDomain() {
        Assertions.assertThatThrownBy(() -> Item.create("Item name greater than 20", Type.hockey_skates.name(), "Description", 20.00))
                .isInstanceOf(RuntimeException.class).hasMessage("Item Name should not exceed 20 characters");

        Assertions.assertThatThrownBy(() -> Item.create("Item name", Type.hockey_skates.name(), RandomString.make(201), 20.00))
                .isInstanceOf(RuntimeException.class).hasMessage("Item Description should not exceed 200 characters");

        Assertions.assertThatThrownBy(() -> Item.create("Item name", Type.hockey_skates.name(), "Description", 0.00))
                .isInstanceOf(RuntimeException.class).hasMessage("Item Cost should be greater than 0");

        Assertions.assertThatThrownBy(() -> Item.create("Item name", "unknown_type", "Description", 20.00))
                .isInstanceOf(RuntimeException.class).hasMessage("type: unknown_type does not exist");
    }

}
