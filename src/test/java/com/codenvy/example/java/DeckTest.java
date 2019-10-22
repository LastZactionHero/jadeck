/*
 *  Copyright (c) Red Hat, Inc. All rights reserved.
 *  Licensed under the MIT License. See LICENSE in the project root for license information.
 *————————————————————————————————————————————*/
package com.codenvy.example.java;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.eclipse.che.examples.Deck;
import org.eclipse.che.examples.Term;
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
    
    @Test
    public void testItShouldSerializeDeckToTerms() {        
        InputStream is = getClass().getClassLoader().getResourceAsStream("deck.json");
        String json = null;
        try {
            json = IOUtils.toString(is, Charset.forName("UTF-8"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Gson g = new Gson();
        Type collectionType = new TypeToken<Deck<Term>>(){}.getType();

        Deck d = g.fromJson(json, collectionType);
        assertEquals(d.size(), 20);
        
        Term t = (Term) d.get(0);
        assertEquals(t.uuid, "f643015f-2e99-4636-853a-24e971bf9a85");
        assertEquals(t.meta.sentence_meaning, "She's an office worker.");
    }
}
