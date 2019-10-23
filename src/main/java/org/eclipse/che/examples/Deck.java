/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Red Hat, Inc. All rights reserved.
 *  Licensed under the MIT License. See LICENSE in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
package org.eclipse.che.examples;

import java.util.ArrayList;
import org.eclipse.che.examples.Term;

public class Deck<Term> extends ArrayList<Term> {
    public static final long serialVersionUID = 1;
    private int idx = 0;

    public Deck() {
    }
    
    public Term next() {
        if(this.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("deck is empty");
        }

        Term t = (Term) this.get(idx);
        this.idx = (this.idx + 1) % this.size();
        return t;
    }
    
    public void success() {
        // Get the previous term idx, as it has been incremented by
        // next
        int sIdx = Math.max(this.idx - 1, 0);
        this.remove(sIdx);
    }
}