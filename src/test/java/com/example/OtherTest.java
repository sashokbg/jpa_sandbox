package com.example;


import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.Locale;

public class OtherTest {
    @Test
    void currency() {
        Currency.getInstance(Locale.forLanguageTag("ar-TN"));
    }
}
