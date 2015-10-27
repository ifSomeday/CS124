/*
 * Will Rice
 * CS 124
 * Tree Analysis using code from the textbook
 */

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TreePerformance {
	
	@SuppressWarnings("rawtypes")
	private static AvlTree avlTreeOrder = new AvlTree(), avlTreeRand = new AvlTree();
	@SuppressWarnings("rawtypes")
	private static BinarySearchTree binarySearchTreeOrder = new BinarySearchTree(), binarySearchTreeRand = new BinarySearchTree();
	@SuppressWarnings("rawtypes")
	private static SplayTree splayTreeOrder = new SplayTree(), splayTreeRand = new SplayTree();

	private static int[] inOrder = new int[100000], randOrder = new int[100000];
	
	public static void main(String args[]){
		
		//create Arrays to read values from
		for(int i = 1; i <= 100000; i++){
			inOrder[i-1] = i;
		}
		randOrder = inOrder.clone();
		
		System.out.println(inOrder[0]);
		
		
		ShuffleArray(randOrder);
		
		//create all 6 arrays
		PopulateArrays();
		
		//first set of search
		search50Stack();
		
		//second set of search
		searchHunnids();
		
		

	}
	
	//Stacks on deck, where da deck at
	@SuppressWarnings("unchecked")
	static void search50Stack(){
		
		System.out.println("Searching 50000 random keys: ");
		
		ShuffleArray(randOrder);
		
		//binary
		Long startRandBinary50k = System.nanoTime();
		for(int i = 0; i < 50000; i++){
			binarySearchTreeRand.contains(randOrder[i]);
		}
		Long endRandBinary50k = System.nanoTime();
		System.out.println("Binary:  " + (endRandBinary50k - startRandBinary50k));
		
		//avl
		Long startRandAvl50k = System.nanoTime();
		for(int i = 0; i < 50000; i++){
			avlTreeRand.contains(randOrder[i]);
		}
		Long endRandAvl50k = System.nanoTime();
		System.out.println("AVL:     " + (endRandAvl50k - startRandAvl50k));
		
		//splay
		Long startRandSplay50k = System.nanoTime();
		for(int i = 0; i < 50000; i++){
			splayTreeRand.contains(randOrder[i]);
		}
		Long endRandSplay50k = System.nanoTime();
		System.out.println("Splay:   " + (endRandSplay50k - startRandSplay50k));

		System.out.println("");
	}
	
	//Throwing hunnids, hunnids.
	@SuppressWarnings("unchecked")
	static void searchHunnids(){
		
		System.out.println("Searching 100 random keys, 500 times each:");
		
		ShuffleArray(randOrder);
		
		//binary
		Long startRandBinaryHunnids = System.nanoTime();
		for(int i = 0; i < 500; i++){
			for(int j = 0; j < 100; j++){
				binarySearchTreeRand.contains(randOrder[i]);
			}
		}
		Long endRandBinaryHunnids = System.nanoTime();
		System.out.println("Binary:  " + (endRandBinaryHunnids - startRandBinaryHunnids));
		
		//avl
		Long startRandAvlHunnids = System.nanoTime();
		for(int i = 0; i < 500; i++){
			for(int j = 0; j < 100; j++){
				avlTreeRand.contains(randOrder[i]);
			}
		}
		Long endRandAvlHunnids = System.nanoTime();
		System.out.println("AVL:     " + (endRandAvlHunnids - startRandAvlHunnids));
		
		//splay
		Long startRandSplayHunnids = System.nanoTime();
		for(int i = 0; i < 500; i++){
			for(int j = 0; j < 100; j++){
				splayTreeRand.contains(randOrder[i]);
			}
		}
		Long endRandSplayHunnids = System.nanoTime();
		System.out.println("Splay:   " + (endRandSplayHunnids - startRandSplayHunnids));
	}
	
	@SuppressWarnings("unchecked")
	static void PopulateArrays(){
		//Ordered Trees
		//All the data is read out of arrays for both kinds of trees so that it wont have an impact on the time
		System.out.println("Ordered tree insertions:");
		
		//binary
		Long startInsertBinaryInOrder = System.nanoTime();
		for(int i = 0; i < 100000; i++){
			binarySearchTreeOrder.insert(inOrder[i]);
		}
		Long endInsertBinaryInOrder = System.nanoTime();
		System.out.println("Binary:  " + (endInsertBinaryInOrder-startInsertBinaryInOrder));
		
		//avl
		Long startInsertAvlInOrder = System.nanoTime();
		for(int i = 0; i < 100000; i++){
			avlTreeOrder.insert(inOrder[i]);
		}
		Long endInsertAvlInOrder = System.nanoTime();
		System.out.println("AVL:     " + (endInsertAvlInOrder-startInsertAvlInOrder));
		
		//splay
		Long startInsertSplayInOrder = System.nanoTime();
		for(int i = 0; i < 100000; i++){
			splayTreeOrder.insert(inOrder[i]);
		}
		Long endInsertSplayInOrder = System.nanoTime();
		System.out.println("Splay:   " + (endInsertSplayInOrder-startInsertSplayInOrder));
		
		System.out.println("");
		
		//Random Trees
		System.out.println("Random tree insertions:");
		
		//binary
		Long startInsertBinaryRand = System.nanoTime();
		for(int i = 0; i < 100000; i++){
			binarySearchTreeRand.insert(inOrder[i]);
		}
		Long endInsertBinaryRand = System.nanoTime();
		System.out.println("Binary:  " + (endInsertBinaryRand - startInsertBinaryRand));
		
		//splay
		Long startInsertAvlRand = System.nanoTime();
		for(int i = 0; i < 100000; i++){
			avlTreeRand.insert(randOrder[i]);
		}
		Long endInsertAvlRand = System.nanoTime();
		System.out.println("AVL:     " + (endInsertAvlRand-startInsertAvlRand));
		
		//avl
		Long startInsertSplayRand = System.nanoTime();
		for(int i = 0; i < 100000; i++){
			splayTreeRand.insert(randOrder[i]);
		}
		Long endInsertSplayRand = System.nanoTime();
		System.out.println("Splay:   " + (endInsertSplayRand-startInsertSplayRand));
		
		System.out.println("");
	}
	
	//Fisher-Yates array shuffle
	static void ShuffleArray(int[] array){
		Random random = ThreadLocalRandom.current();
		for(int i = array.length - 1; i > 0; i--){
			int index = random.nextInt(i+1);
			int a = array[index];
			array[index] = array[i];
			array[i] = a;
		}
	}

}
