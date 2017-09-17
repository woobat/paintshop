package com.gondor.kata.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by coding on 18/09/2017.
 */
public class PaletteTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void isValid() throws Exception {
        assertEquals(true, Palette.isValid("GLOSS"));
        assertEquals(true, Palette.isValid("MATTE"));
        assertEquals(false, Palette.isValid("GLOSSY"));
        assertEquals(false, Palette.isValid("MATTTTEEE"));
    }

    @Test
    public void isValidShortName() throws Exception {
        assertEquals(true, Palette.isValidShortName("G"));
        assertEquals(true, Palette.isValidShortName("M"));
        assertEquals(false, Palette.isValidShortName("GG"));
        assertEquals(false, Palette.isValidShortName("MM"));
    }

    @Test
    public void fromShortName() throws Exception {
        assertEquals(Palette.GLOSS, Palette.fromShortName("G"));
        assertEquals(Palette.MATTE, Palette.fromShortName("M"));
    }

    @Test
    public void fromShortNameCanHandleUnexpectedValue() throws Exception {
        ex.expect(IllegalArgumentException.class);
        assertEquals(Palette.GLOSS, Palette.fromShortName("GG"));
    }

}