/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2011 OpenConcerto, by ILM Informatique. All rights reserved.
 * 
 * The contents of this file are subject to the terms of the GNU General Public License Version 3
 * only ("GPL"). You may not use this file except in compliance with the License. You can obtain a
 * copy of the License at http://www.gnu.org/licenses/gpl-3.0.html See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each file.
 */
 
 package org.openconcerto.ui.preferences;

import javax.swing.tree.DefaultMutableTreeNode;

public class PrefTreeNode extends DefaultMutableTreeNode {
    private Class<? extends PreferencePanel> c;
    private String name;
    private String[] keywords;
    private boolean match = true;
    private boolean bold = false;

    public PrefTreeNode(Class<? extends PreferencePanel> c, String name, String[] keywords) {
        this(c, name, keywords, false);
    }

    public PrefTreeNode(Class<? extends PreferencePanel> c, String name, String[] keywords, boolean bold) {
        this.name = name;
        this.keywords = keywords;

        // if (!PreferencePanel.class.isAssignableFrom(c))
        // throw new IllegalArgumentException();
        this.bold = bold;
        this.c = c;
    }

    public PreferencePanel getPanel() {
        try {
            return this.c.newInstance();
        } catch (Exception e) {
            System.err.println("Cannot Instantiate class:" + this.c.getCanonicalName());
            e.printStackTrace();
            return null;
        }
    }

    public boolean isBold() {
        return this.bold;
    }

    public boolean match(String[] in) {
        for (int i = 0; i < in.length; i++) {
            String inValue = in[i].toLowerCase();
            for (int j = 0; j < this.keywords.length; j++) {
                String key = this.keywords[j].toLowerCase();
                if (key.indexOf(inValue) >= 0) {
                    System.out.println("--- match " + this.name);
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName();
    }

    /**
     * @return Returns the match.
     */
    boolean isMatching() {
        return this.match;
    }

    /**
     * @param match The match to set.
     */
    void setMatch(boolean match) {
        this.match = match;
    }

}