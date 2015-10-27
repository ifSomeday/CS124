/*
 * Will Rice
 * CS 124
 * 
 * Tree based implementation of a Polish Notation program
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolishNotation {

	public static String value, operators = "+/*-";
	public static Pattern pattern;
	public static Matcher matcher;
	public static Node root = new Node();

	public static void main(String args[]) throws FileNotFoundException{
		
		pattern = Pattern.compile("([\\S]+)");
		File infile;
		try{
			infile = new File("input.txt");
			Scanner scanner = new Scanner(infile);
			PrintWriter writer = new PrintWriter("output.txt");
	
	
			while (scanner.hasNextLine()) {
				matcher = pattern.matcher(scanner.nextLine());
				matcher.find();
				root.setData(matcher.group());
				populateNode(root);
				writer.println(evaluateTree(root));
	
			}
			writer.close();
			scanner.close();
		} catch (FileNotFoundException e){
			PrintWriter writer = new PrintWriter("output.txt");
			writer.write("No file 'input.txt' found. Aborting.");
			writer.close();
			System.out.println("No file 'input.txt' found. Aborting.");
		}
	}

	//Populates the tree one node at a time
	public static void populateNode(Node node) {
		if (node.getLeft() == null) {
			matcher.find();
			value = matcher.group();
			node.setLeft(new Node(value));
			if (operators.contains(value)) {
				populateNode(node.getLeft());
			}

		}
		if (node.getRight() == null) {
			matcher.find();
			value = matcher.group();
			node.setRight(new Node(value));
			if (operators.contains(value)) {
				populateNode(node.getRight());
			}

		}

	}

	//evaluates tree
	public static int evaluateTree(Node node) {
		if (operators.contains(node.getLeft().getData())) {
			evaluateTree(node.getLeft());
		}
		if (operators.contains(node.getRight().getData())) {
			evaluateTree(node.getRight());
		}

		switch (node.getData()) {
		case "*":
			node.setData(Integer.toString(
					Integer.parseInt(node.getLeft().getData()) * Integer.parseInt(node.getRight().getData())));
			break;
		case "/":
			node.setData(Integer.toString(
					Integer.parseInt(node.getLeft().getData()) / Integer.parseInt(node.getRight().getData())));
			break;
		case "+":
			node.setData(Integer.toString(
					Integer.parseInt(node.getLeft().getData()) + Integer.parseInt(node.getRight().getData())));
			break;
		case "-":
			node.setData(Integer.toString(
					Integer.parseInt(node.getLeft().getData()) - Integer.parseInt(node.getRight().getData())));
			break;
		default:
			break;
		}
		node.setLeft(null);
		node.setRight(null);

		return (Integer.parseInt(node.getData()));
	}

}

//node definition for the tree
class Node {
	String data;
	Node right;
	Node left;

	public Node() {

	}

	public Node(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public boolean isleaf() {
		if (this.right == null && this.left == null)
			return true;
		return false;
	}

}