package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import comp1110.ass2.WarringStatesGame.*;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class rearrangeTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void testEmpty() {
        assertFalse("Null  placement string is not OK, but passed", WarringStatesGame.isPlacementWellFormed(null));
        assertFalse("Empty placement string is not OK, but passed", WarringStatesGame.isPlacementWellFormed(""));
    }

    @Test
    public void testSingleKingdom(){
        for(int i = 0; i < One.length; i ++) {
            assertFalse("The rearrange for "+One[i]+" should be "+one[i]+" but got "+WarringStatesGame.rearrange(One[i]),
                    WarringStatesGame.rearrange(One[i]) == one[i]);
        }
    }

    @Test
    public void testDoubleKindoms() {
        for(int i = 0; i < Two.length; i ++) {
            assertFalse("The rearrange for "+Two[i]+" should be "+two[i]+" but got "+WarringStatesGame.rearrange(Two[i]),
                    WarringStatesGame.rearrange(Two[i]) == two[i]);
        }
    }

    @Test
    public void testComplexKindoms() {
        for(int i = 0; i < Complex.length; i ++) {
            assertFalse("The rearrange for "+Two[i]+" should be "+complex[i]+" but got "+WarringStatesGame.rearrange(Complex[i]),
                    WarringStatesGame.rearrange(Complex[i]) == complex[i]);
        }
    }
    static final String[] One = {
            "a5a1a2", "b7b4b1","c1c2"
    };
    String[] Two = {
            "a0b1c3","a0a7a1c5","d3g3d4"
    };
    String[] Complex = {
            "g1c4a0b6b4b5b1","f2g0a1b2a5"
    };
    String[] one = {
            "a1a2a5","b1b4b7","c1c2"
    };
    String[] two = {
            "a0b1c3","a0a1a7c5","d3d4g3"
    };
    String[] complex = {
            "a0b1b4b5b6c4g1","a1a5b2f2g0"
    };
}
