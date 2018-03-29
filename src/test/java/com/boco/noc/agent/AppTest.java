package com.boco.noc.agent;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		System.setProperty("user.dir", "D:\\");
		System.out.println(System.getProperty("user.dir"));
	}
}
