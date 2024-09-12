/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab02;

/**
 * Implement the Sequence ADT from Lecture 2.2
 *
 * The items in the sequence are indexed from 1 (as opposed to Java arrays that
 * are indexed from 0)
 */
public class Sequence {

    private static final int INITIAL_CAPACITY = 100;

    private int capacity;
    private int length;
    private int[] items;

    public Sequence() {
        this(INITIAL_CAPACITY, new int[]{});
    }

    public Sequence(int capacity, int[] items) {
        if (capacity < items.length) {
            throw new IllegalArgumentException("Capacity must be greater than item count");
        }
        this.capacity = capacity;
        this.length = items.length;
        this.items = new int[capacity];
        for (int i=0 ; i<items.length ; i++) {
            this.items[i] = items[i];
        }
    }

    /**
     * @return The number of items in the sequence
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @return the number of "buckets" currently allocated
     */
    int getCapacity() {
        return this.capacity;
    }

    /**
     * Return the item stored at the given index
     *
     * @param index the index of the desired item, starting at 1
     * @return the item at the given index.
     */
    public int get(int index) {
        if (index < 1 || index > length) {
            throw new IllegalArgumentException("Invalid index!");
        }
        return this.items[index - 1];
    }

    /**
     * Append the given item at the end of the sequence
     *
     * @param item the item that must be inserted
     */
    public void insert(int item, int index) {
        if(index > capacity*2){   //Checks if the new index is inside the bounds of a resized array
            throw new IllegalArgumentException("index out of bounds");
        }
        if (length == capacity) {       // Creates a new array twice the size if this one is full
            int[] newItems = new int[capacity * 2];
            for (int i=0 ; i<length ; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
            capacity *= 2;
        }
        if
        (index > length && index < capacity){  // Checks if adding the new item would create holes
                                                // in the array, if so, append at the end.
            this.items[length] = item;
        }
        else {                      // otherwise move elements until the new element can be placed.
            for (int i = length; i >= index; i--) {
                items[i] = items[i - 1];
            }
            this.items[index - 1] = item;
        }
        this.length++;

    }



    /**
     * Remove the index at the given index
     *
     * @param index the index that must be removed.
     */
    public void remove(int index) {
        if (length == 0) {
            throw new IllegalArgumentException("Cannot remove from an empty sequence");
        }
        if(index == this.length){   //removes element at the end of array
            this.items[index-1] = 0;
        }
        else {              // overwrites the element to be deleted by the succeeding element.
            for (int i = index; i < this.length; i++) {
                this.items[i - 1] = this.items[i];
            }
            this.items[this.length] = 0;        // removes the last element in the list now in
                                                // position length-1
        }
        this.length--;
        if(this.length <= capacity/4){      //Checks if the array is 1/4 of the capacity
            int[] newItems = new int[this.capacity/2];
            for (int i = 0; i <this.length; i++){   // moves the relevant items to a smaller array.
                newItems[i] = this.items[i];
            }
            this.items = newItems;
            this.capacity = this.capacity/2;  // halves the capacity.
        }
    }

    /**
     * Find a index where the given item can be found. Returns 0 if that item cannot
     * be found.
     *
     * @param item the item whose index must be found
     * @return an
     */
    public int search(int item) {
        int index = 1;
        while(index <= this.length){
            if(this.items[index-1] == item){
                return index;
            }
            index++;
        }
        return 0;
    }

    /**
     * Find both the smallest and the largest items in the sequence.
     *
     * @return an array of length two where the first entry is the minimum and the
     *         second the maximum
     */
    public int[] extrema() {
        if(this.length == 0) {
            throw new IllegalStateException("Cannot find extrema in an empty sequence");
        }
        int min = this.items[0];
        int max = this.items[0];
        for (int i = 1; i < this.length; i++) {
            if (this.items[i] < min) {
                min = this.items[i];
            }
            if (this.items[i] > max) {
                max = this.items[i];
            }
        }
        return new int[]{min, max};
    }

    /**
     * Check whether the given sequence contains any duplicate item
     *
     * @return true if the sequence has the the same items at multiple indices
     */
    public boolean hasDuplicate() {
        boolean duplicate = false;
        for (int i = 0; i < this.length; i++) {
            for (int j = i + 1; j < this.length; j++) {
                if (this.items[i] == this.items[j]) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }


    /**
     * Convert the sequence into an Java array
     */
    public int[] toArray() {
        return items;
    }

}
