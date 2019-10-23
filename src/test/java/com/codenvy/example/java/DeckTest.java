/*
 *  Copyright (c) Red Hat, Inc. All rights reserved.
 *  Licensed under the MIT License. See LICENSE in the project root for license information.
 *————————————————————————————————————————————*/
package com.codenvy.example.java;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.eclipse.che.examples.Deck;
import org.eclipse.che.examples.Term;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
@RunWith(JUnit4.class)

public class DeckTest {
    Deck<Term> deck = null;
    
    @Before public void initializeDeck() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("deck.json");
        String json = "";
        try {
            json = IOUtils.toString(is, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }        

        Type collectionType = new TypeToken<Deck<Term>>(){}.getType();
        Gson g = new Gson();
        deck = g.fromJson(json, collectionType);
    }
    

    @Test public void constructorShouldSerializeTerms() {        
        assertEquals(deck.size(), 20);

        Term t = (Term) deck.get(0);
        assertEquals(t.uuid, "f643015f-2e99-4636-853a-24e971bf9a85");
        assertEquals(t.meta.sentence_meaning, "She's an office worker.");
    }
    
    @Test public void nextShouldReturnNextTerm() {
        String termFirstUUID = "f643015f-2e99-4636-853a-24e971bf9a85";
        String termSecondUUID = "71e0e3af-58f4-45f6-b142-7267457f278c";
        int termCount = deck.size();

        // It fetches the first term
        Term t = deck.next();
        assertEquals(t.uuid, termFirstUUID);

        // It fetches the second term
        t = deck.next();
        assertEquals(t.uuid, termSecondUUID);

        // It loops over to the first term again
        for(int i = 0; i < (termCount - 2); i++) {
            deck.next();
        }
        t = deck.next();
        assertEquals(t.uuid, termFirstUUID);
    }
    
    @Test public void nextShouldRaiseExceptionIfNoTerms() {
        deck.clear();
        assertEquals(0, deck.size()); // Assumption;
        
        try {
            deck.next();
            fail(); // Should never reach here;        
        } catch(ArrayIndexOutOfBoundsException e) {
            
        }
    }
    
    @Test public void successShouldRemoveTermFromDeck() {
        assertEquals(20, deck.size()); // Assumption

        Term t = deck.next(); // Get the first term
        assertTrue(deck.contains(t)); // Assumption

        deck.success();
        
        assertEquals(19, deck.size()); // Deck size has been reduced by 1
        assertFalse(deck.contains(t));
    }
}
