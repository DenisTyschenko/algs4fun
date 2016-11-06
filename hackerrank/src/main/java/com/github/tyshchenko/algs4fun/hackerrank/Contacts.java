package com.github.tyshchenko.algs4fun.hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://www.hackerrank.com/challenges/ctci-contacts">Tries: Contacts</a>
 * <p>
 * We're going to make our own Contacts application! The application must perform two types of operations:
 * <ul>
 *  <li>add name, where {@code name}is a string denoting a contact name. This must store  as a new contact in the application.</li>
 *  <li>find partial, where  {@code partial} is a string denoting a partial name to search the application for. It must count the number of contacts starting with  and print the count on a new line.</li>
 * </ul>
 * Given {@code n} sequential add and find operations, perform each operation in order.
 * </p>
 * @author denis on 11/6/16.
 */
public class Contacts {

    private final TryNode root = new TryNode();

    public void add(String name) {
        char[] chars = name.toCharArray();
        TryNode node = root;

        for (char ch : chars) {
            node = node.has(ch) ? node.getNextBy(ch) : node.add(ch);
        }
        // handle duplications, like add "ba" twice, however differentiate between
        // add("hacker") and then add("h").
        if (!node.isCompleted()) {
            // if added name is not yet preset, increase solution number for each node in path
            node.complete();
            node = root;
            for (char ch : chars) {
                node = node.getNextBy(ch);
                node.increaseCompleted();
            }
        }
    }

    /**
     * @return number of contact names starting with partial, 0 if no match found
     */
    public int find(String partial) {
        char[] chars = partial.toCharArray();
        TryNode node = root;
        for (char ch : chars) {
            if (node.has(ch)) {
                node = node.getNextBy(ch);
            } else {
                return 0;
            }
        }
        return node.getNumOfCompletedSiblings();
    }

    private class TryNode {
        private final Map<Character, TryNode> siblings = new HashMap<>();
        private int numOfCompletedSiblings;
        private boolean completed;

        public boolean isCompleted() {
            return completed;
        }

        public void complete() {
            this.completed = true;
        }

        public void increaseCompleted() {
            numOfCompletedSiblings++;
        }

        public int getNumOfCompletedSiblings() {
            return numOfCompletedSiblings;
        }

        public TryNode add(Character ch) {
            TryNode node = new TryNode();
            siblings.put(ch, node);
            return node;
        }

        public boolean has(Character ch) {
            return siblings.containsKey(ch);
        }

        public TryNode getNextBy(Character ch) {
            return siblings.get(ch);
        }

    }

}
