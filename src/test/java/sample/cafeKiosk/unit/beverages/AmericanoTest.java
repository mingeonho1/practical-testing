package sample.cafeKiosk.unit.beverages;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmericanoTest {

    @Test
    void getName() {
        Americano americano = new Americano();

//        assertEquals(americano.getName(), "아메리카노");
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @Test
    void gerPrice() {
        Americano americano = new Americano();

        assertThat(americano.getPrice()).isEqualTo(4000);

    }
}