package com.test.mobile.data;

import java.util.Comparator;

/**
 * Comparator for Item Object
 */

public class itemComparator {
    public static Comparator<Item> nameComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static Comparator<Item> statusComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getStatus().compareTo(o2.getStatus());
        }
    };

    public static Comparator<Item> qtyComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return (int)o1.getQuantity() - (int)(o2.getQuantity());
        }
    };

    public static Comparator<Item> siteComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getSite().compareTo(o2.getSite());
        }
    };

    public static Comparator<Item> batchComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getBatch().compareTo(o2.getBatch());
        }
    };



}