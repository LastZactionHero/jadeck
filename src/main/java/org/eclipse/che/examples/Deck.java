/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Red Hat, Inc. All rights reserved.
 *  Licensed under the MIT License. See LICENSE in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
package org.eclipse.che.examples;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import org.eclipse.che.examples.Term;

public class Deck<Term> extends ArrayList<Term> {
    public Deck() {
        Term t;
    }
//    public Deck(String json) {1
//        Type collectionType = new TypeToken<Collection<Term>>(){}.getType();
//        
//        Gson g = new Gson();
//        Collection<Term> terms = g.fromJson(json, collectionType);
//        Term t = (Term) terms.toArray()[0]   
}