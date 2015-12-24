package com.acme;

import org.junit.Test;
import static org.junit.Assert.*;

public class BlackBoxTest {
    @Test
    public void shouldFindTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything() {
        assertEquals(42, BlackBox.getAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything());
    }
}
